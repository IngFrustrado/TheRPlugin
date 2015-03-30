package com.jetbrains.ther.interpreter;

import com.google.common.collect.Lists;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModifiableModelsProvider;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.OrderEntry;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.roots.impl.OrderEntryUtil;
import com.intellij.openapi.roots.libraries.Library;
import com.intellij.openapi.roots.libraries.LibraryTable;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.ther.TheRHelpersLocator;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TheRInterpreterConfigurable implements SearchableConfigurable, Configurable.NoScroll{
  private JPanel myMainPanel;
  private final Project myProject;
  private final TextFieldWithBrowseButton myInterpreterField;
  private final TextFieldWithBrowseButton mySourcesField;
  public static final String THE_R_LIBRARY = "R Library";
  public static final String THE_R_SKELETONS = "R Skeletons";
  public static final String The_R_USER_SKELETONS = "R User Skeletons";

  TheRInterpreterConfigurable(Project project) {
    myProject = project;

    final GridBagLayout layout = new GridBagLayout();
    myMainPanel = new JPanel(layout);
    final JLabel interpreterLabel = new JLabel("Interpreter:");
    final JLabel sourcesLabel = new JLabel("Sources:");
    myInterpreterField = new TextFieldWithBrowseButton();
    mySourcesField = new TextFieldWithBrowseButton();
    final FileChooserDescriptor interpreterDescriptor = FileChooserDescriptorFactory.createSingleLocalFileDescriptor();
    myInterpreterField.addBrowseFolderListener("Choose interpreter path", "Choose interpreter path", myProject, interpreterDescriptor);
    final FileChooserDescriptor sourcesDescriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor();
    mySourcesField.addBrowseFolderListener("Choose R sources folder", "Choose R sources folder", myProject, sourcesDescriptor);

    final GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.insets = new Insets(2,2,2,2);
    c.anchor = GridBagConstraints.NORTH;

    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 0.;
    c.weighty = 0.;
    final JPanel interpreterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    interpreterPanel.add(interpreterLabel);
    myMainPanel.add(interpreterPanel, c);

    c.gridx = 1;
    c.gridy = 0;
    c.weightx = 1.;
    myMainPanel.add(myInterpreterField, c);

    c.gridx = 0;
    c.gridy = 1;
    c.weightx = 0.;
    c.weighty = 1.;
    final JPanel sourcesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    sourcesPanel.add(sourcesLabel);
    myMainPanel.add(sourcesPanel, c);

    c.gridx = 1;
    c.gridy = 1;
    c.weightx = 1.;
    myMainPanel.add(mySourcesField, c);
  }

  @NotNull
  @Override
  public String getId() {
    return getClass().getName();
  }

  @Nullable
  @Override
  public Runnable enableSearch(String option) {
    return null;
  }

  @Nls
  @Override
  public String getDisplayName() {
    return "The R Interpreter";
  }

  @Nullable
  @Override
  public String getHelpTopic() {
    return null;
  }

  @Nullable
  @Override
  public JComponent createComponent() {
    return myMainPanel;
  }

  @Override
  public boolean isModified() {
    final TheRInterpreterService interpreterService = TheRInterpreterService.getInstance();
    return !interpreterService.getInterpreterPath().equals(myInterpreterField.getText()) ||
           !interpreterService.getSourcesPath().equals(mySourcesField.getText());
  }

  @Override
  public void apply() throws ConfigurationException {
    final TheRInterpreterService interpreterService = TheRInterpreterService.getInstance();
    final String interpreterPath = myInterpreterField.getText();
    final String sourcesPath = mySourcesField.getText();

    final String oldSourcesPath = interpreterService.getSourcesPath();
    if (!sourcesPath.equals(oldSourcesPath)) {
      if (!StringUtil.isEmptyOrSpaces(oldSourcesPath)) {
        detachLibrary();
      }
      if (!StringUtil.isEmptyOrSpaces(sourcesPath)) {
        final ArrayList<String> paths = getSourcePaths(sourcesPath);
        if (!paths.isEmpty())
          attachLibrary(paths);
      }
    }

    generateSkeletons();
    interpreterService.setSourcesPath(sourcesPath);
    interpreterService.setInterpreterPath(interpreterPath);
  }

  private void generateSkeletons() {
    final ModifiableModelsProvider modelsProvider = ModifiableModelsProvider.SERVICE.getInstance();
    final Application application = ApplicationManager.getApplication();

    application.invokeLater(new Runnable() {
      @Override
      public void run() {
        ProgressManager.getInstance().run(new Task.Backgroundable(myProject, "Updating skeletons", false) {
          @Override
          public void run(@NotNull ProgressIndicator indicator) {

            final TheRSkeletonGenerator generator = new TheRSkeletonGenerator();
            generator.runSkeletonGeneration();
          }});

        application.runWriteAction(new Runnable() {
          @Override
          public void run() {
            // add all paths to library
            final String path = TheRSkeletonGenerator.getSkeletonsPath(TheRInterpreterService.getInstance().getInterpreterPath());
            generateLibrary(THE_R_SKELETONS, path, modelsProvider);
            final String userSkeletonsPath =  TheRHelpersLocator.getHelperPath("r-user-skeletons");
            generateLibrary(The_R_USER_SKELETONS, userSkeletonsPath, modelsProvider);
          }
        });
      }
    });
  }

  private void generateLibrary(final String name, final String path, ModifiableModelsProvider modelsProvider) {
    final LibraryTable.ModifiableModel model = modelsProvider.getLibraryTableModifiableModel(myProject);
    Library library = model.getLibraryByName(name);
    if (library == null) {
      library = model.createLibrary(name);
    }
    fillLibrary(library, Lists.newArrayList(path));
    model.commit();
    final Library.ModifiableModel libModel = library.getModifiableModel();
    libModel.commit();
    final Module[] modules = ModuleManager.getInstance(myProject).getModules();
    for (Module module : modules) {
      final ModifiableRootModel modifiableModel = modelsProvider.getModuleModifiableModel(module);
      modifiableModel.addLibraryEntry(library);
      modelsProvider.commitModuleModifiableModel(modifiableModel);
    }
  }

  private ArrayList<String> getSourcePaths(@NotNull final String sourcesPath) {
    final ArrayList<String> paths = Lists.newArrayList();
    final VirtualFile file = LocalFileSystem.getInstance().findFileByPath(sourcesPath);
    if (file != null) {
      final VirtualFile libFile = file.findFileByRelativePath("src/library");
      if (libFile != null) {
        final VirtualFile[] children = libFile.getChildren();
        for (VirtualFile child : children) {
          final VirtualFile rDirectory = child.findFileByRelativePath("R");
          if (rDirectory != null)
            paths.add(rDirectory.getPath());
        }
      }
    }
    return paths;
  }

  @Override
  public void reset() {
    final TheRInterpreterService interpreterService = TheRInterpreterService.getInstance();
    final String interpreterPath = interpreterService.getInterpreterPath();
    myInterpreterField.setText(interpreterPath != null ? interpreterPath : "");
    final String sourcesPath = interpreterService.getSourcesPath();
    mySourcesField.setText(sourcesPath != null ? sourcesPath : "");
  }

  @Override
  public void disposeUIResources() {
  }

  public void detachLibrary() {
    final ModifiableModelsProvider modelsProvider = ModifiableModelsProvider.SERVICE.getInstance();
    ApplicationManager.getApplication().runWriteAction(new Runnable() {
      @Override
      public void run() {
        // add all paths to library
        final LibraryTable.ModifiableModel model = modelsProvider.getLibraryTableModifiableModel(myProject);
        final Library library = model.getLibraryByName(THE_R_LIBRARY);
        if (library != null) {

          final Module[] modules = ModuleManager.getInstance(myProject).getModules();
          for (Module module : modules) {
            final ModifiableRootModel modifiableModel = modelsProvider.getModuleModifiableModel(module);
            OrderEntry entry = OrderEntryUtil.findLibraryOrderEntry(modifiableModel, THE_R_LIBRARY);
            if (entry != null) {
              modifiableModel.removeOrderEntry(entry);
              modelsProvider.commitModuleModifiableModel(modifiableModel);
            }
            else {
              modelsProvider.disposeModuleModifiableModel(modifiableModel);
            }
          }
          model.removeLibrary(library);
          model.commit();
        }
      }
    });
  }

  public void attachLibrary(@NotNull final List<String> paths) {
    final ModifiableModelsProvider modelsProvider = ModifiableModelsProvider.SERVICE.getInstance();
    ApplicationManager.getApplication().runWriteAction(new Runnable() {
      @Override
      public void run() {
        // add all paths to library
        final LibraryTable.ModifiableModel model = modelsProvider.getLibraryTableModifiableModel(myProject);
        Library library = model.getLibraryByName(THE_R_LIBRARY);
        if (library == null) {
          library = model.createLibrary(THE_R_LIBRARY);
        }
        fillLibrary(library, paths);
        model.commit();
        final Library.ModifiableModel libModel = library.getModifiableModel();
        libModel.commit();
        final Module[] modules = ModuleManager.getInstance(myProject).getModules();
        for (Module module : modules) {
          final ModifiableRootModel modifiableModel = modelsProvider.getModuleModifiableModel(module);
          modifiableModel.addLibraryEntry(library);
          modelsProvider.commitModuleModifiableModel(modifiableModel);
        }
      }

    });

  }

  private static void fillLibrary(@NotNull final Library lib, @NotNull final List<String> paths) {
    Library.ModifiableModel modifiableModel = lib.getModifiableModel();
    for (String root : lib.getUrls(OrderRootType.CLASSES)) {
      modifiableModel.removeRoot(root, OrderRootType.CLASSES);
    }
    for (String dir : paths) {
      final VirtualFile pathEntry = LocalFileSystem.getInstance().findFileByPath(dir);
      if (pathEntry != null) {
        modifiableModel.addRoot(pathEntry, OrderRootType.CLASSES);
      }
      else {
        modifiableModel.addRoot("file://"+dir, OrderRootType.CLASSES);
      }
    }
    modifiableModel.commit();
  }

}

package com.jetbrains.ther.run.configuration;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.ther.TheRFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class TheRRunConfigurationUtils {

  public static void checkConfiguration(@NotNull final TheRRunConfiguration runConfiguration) throws ConfigurationException {
    final List<String> unspecifiedParameters = new ArrayList<String>();

    if (StringUtil.isEmptyOrSpaces(runConfiguration.getScriptPath())) {
      unspecifiedParameters.add("script");
    }

    if (StringUtil.isEmptyOrSpaces(runConfiguration.getWorkingDirectoryPath())) {
      unspecifiedParameters.add("working directory");
    }

    if (!unspecifiedParameters.isEmpty()) {
      final String prefix = unspecifiedParameters.size() == 1
                            ? "There is unspecified parameter in R run configuration: "
                            : "There are unspecified parameters in R run configuration: ";

      throw new ConfigurationException(
        prefix + StringUtil.join(unspecifiedParameters, ", ")
      );
    }
  }

  @Nullable
  static String suggestedName(@NotNull final TheRRunConfigurationParams runConfigurationParams) {
    final String scriptPath = runConfigurationParams.getScriptPath();

    if (StringUtil.isEmptyOrSpaces(scriptPath)) return null;

    final String name = new File(scriptPath).getName();
    final String dotAndExtension = "." + TheRFileType.INSTANCE.getDefaultExtension();

    if (name.length() > dotAndExtension.length() && StringUtil.endsWithIgnoreCase(name, dotAndExtension)) {
      return name.substring(0, name.length() - dotAndExtension.length());
    }

    return name;
  }

  @Nullable
  static String suggestedWorkingDirectoryPath(@NotNull final TheRRunConfigurationParams runConfigurationParams) {
    final String scriptPath = runConfigurationParams.getScriptPath();

    if (StringUtil.isEmptyOrSpaces(scriptPath)) return null;

    return new File(scriptPath).getParent();
  }

  static void setSuggestedWorkingDirectoryPathIfNotSpecified(@NotNull final TheRRunConfigurationParams runConfigurationParams) {
    if (!StringUtil.isEmptyOrSpaces(runConfigurationParams.getWorkingDirectoryPath())) {
      return;
    }

    final String suggestedWorkingDirectoryPath = TheRRunConfigurationUtils.suggestedWorkingDirectoryPath(runConfigurationParams);

    if (suggestedWorkingDirectoryPath != null) {
      runConfigurationParams.setWorkingDirectoryPath(suggestedWorkingDirectoryPath);
    }
  }
}

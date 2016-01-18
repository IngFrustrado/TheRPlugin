package com.jetbrains.ther.ui.graphics;

import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.testFramework.PlatformTestCase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;

public class TheRGraphicsStateImplTest extends PlatformTestCase {

  @NotNull
  private VirtualFile mySnapshotDir;

  @NotNull
  private TheRGraphicsStateImpl myState;

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    final VirtualFile snapshotDir = getVirtualFile(createTempDir(getClass().getSimpleName()));
    assert snapshotDir != null;

    mySnapshotDir = snapshotDir;
    myState = new TheRGraphicsStateImpl(snapshotDir);

    Disposer.register(getProject(), myState);

    assertEmpty();
  }

  public void testIllegalNext() throws IOException {
    try {
      myState.next();

      fail("State successfully moved forward");
    }
    catch (final NoSuchElementException ignore) {
    }
  }

  public void testIllegalPrevious() throws IOException {
    try {
      myState.previous();

      fail("State successfully moved backward");
    }
    catch (final NoSuchElementException ignore) {
    }
  }

  public void testReset() throws FileNotFoundException {
    final VirtualFile file = createChildData(mySnapshotDir, "snapshot_1.png");
    final TheRGraphicsState.Listener listener = mock(TheRGraphicsState.Listener.class);

    myState.refresh(false);

    assertSizePreviousCurrentNext(1, false, null, true);

    myState.next();

    assertSizePreviousCurrentNext(1, false, file, false);

    myState.addListener(listener);
    verifyZeroInteractions(listener);

    myState.reset();

    verify(listener, times(1)).onReset();
    assertEmpty();

    verifyNoMoreInteractions(listener);
  }

  public void testDispose() throws FileNotFoundException {
    final VirtualFile file = createChildData(mySnapshotDir, "snapshot_1.png");

    myState.refresh(false);

    assertSizePreviousCurrentNext(1, false, null, true);

    myState.next();

    assertSizePreviousCurrentNext(1, false, file, false);

    Disposer.dispose(myState);

    createChildData(mySnapshotDir, "snapshot_2.png");

    myState.refresh(false);

    assertSizePreviousCurrentNext(1, false, file, false);
  }

  public void testUpdateSnapshot() throws IOException {
    final VirtualFile file1 = createChildData(mySnapshotDir, "snapshot_1.png");
    final VirtualFile file2 = createChildData(mySnapshotDir, "snapshot_2.png");
    final TheRGraphicsState.Listener listener = mock(TheRGraphicsState.Listener.class);

    myState.refresh(false);

    assertSizePreviousCurrentNext(2, false, null, true);

    myState.next();

    assertSizePreviousCurrentNext(2, false, file1, true);

    myState.next();

    assertSizePreviousCurrentNext(2, true, file2, false);

    myState.addListener(listener);

    file1.setBinaryContent("abc".getBytes());

    myState.refresh(false);

    assertSizePreviousCurrentNext(2, true, file2, false);

    verifyZeroInteractions(listener);
  }

  public void testUpdateAnotherSnapshot() throws IOException {
    final VirtualFile file = getVirtualFile(createTempFile("snapshot_1.png", "abc"));
    assert file != null;
    final TheRGraphicsState.Listener listener = mock(TheRGraphicsState.Listener.class);

    myState.refresh(false);

    assertEmpty();

    myState.addListener(listener);

    file.setBinaryContent("abc".getBytes());

    myState.refresh(false);

    assertEmpty();

    verifyZeroInteractions(listener);
  }

  public void testUpdateNotSnapshot() throws IOException {
    final VirtualFile file = createChildData(mySnapshotDir, "snapshot.png");
    final TheRGraphicsState.Listener listener = mock(TheRGraphicsState.Listener.class);

    myState.refresh(false);

    assertEmpty();

    myState.addListener(listener);

    file.setBinaryContent("abc".getBytes());

    myState.refresh(false);

    assertEmpty();

    verifyZeroInteractions(listener);
  }

  public void testUpdateCurrentSnapshot() throws IOException {
    final VirtualFile file = createChildData(mySnapshotDir, "snapshot_1.png");
    final TheRGraphicsState.Listener listener = mock(TheRGraphicsState.Listener.class);

    myState.refresh(false);

    assertSizePreviousCurrentNext(1, false, null, true);

    myState.next();

    assertSizePreviousCurrentNext(1, false, file, false);

    myState.addListener(listener);
    verifyZeroInteractions(listener);

    file.setBinaryContent("abc".getBytes());

    myState.refresh(false);

    verify(listener, times(1)).onUpdate();
    assertSizePreviousCurrentNext(1, false, file, false);

    verifyNoMoreInteractions(listener);
  }

  public void testCreateSnapshot() throws FileNotFoundException {
    final TheRGraphicsState.Listener listener = mock(TheRGraphicsState.Listener.class);

    myState.addListener(listener);
    verifyZeroInteractions(listener);

    final VirtualFile file = createChildData(mySnapshotDir, "snapshot_1.png");

    myState.refresh(false);

    verify(listener, times(1)).onStarted();
    assertSizePreviousCurrentNext(1, false, null, true);

    myState.next();

    verify(listener, times(1)).onUpdate();
    assertSizePreviousCurrentNext(1, false, file, false);

    verifyNoMoreInteractions(listener);
  }

  public void testCreateAnotherSnapshot() throws IOException {
    final TheRGraphicsState.Listener listener = mock(TheRGraphicsState.Listener.class);

    myState.addListener(listener);

    getVirtualFile(createTempFile("snapshot_1.png", "abc"));

    myState.refresh(false);

    assertEmpty();

    verifyZeroInteractions(listener);
  }

  public void testCreateNotSnapshot() throws FileNotFoundException {
    final TheRGraphicsState.Listener listener = mock(TheRGraphicsState.Listener.class);

    myState.addListener(listener);

    createChildData(mySnapshotDir, "snapshot.png");

    myState.refresh(false);

    assertEmpty();

    verifyZeroInteractions(listener);
  }

  public void testRemoveSnapshot() throws IOException {
    final VirtualFile file1 = createChildData(mySnapshotDir, "snapshot_1.png");
    final VirtualFile file2 = createChildData(mySnapshotDir, "snapshot_2.png");
    final TheRGraphicsState.Listener listener = mock(TheRGraphicsState.Listener.class);

    myState.refresh(false);

    assertSizePreviousCurrentNext(2, false, null, true);

    myState.next();

    assertSizePreviousCurrentNext(2, false, file1, true);

    myState.addListener(listener);

    file2.delete(this);

    myState.refresh(false);

    assertSizePreviousCurrentNext(1, false, file1, false);

    verifyZeroInteractions(listener);
  }

  public void testRemoveCurrentSnapshotWithNext() throws IOException {
    final VirtualFile file1 = createChildData(mySnapshotDir, "snapshot_1.png");
    final VirtualFile file2 = createChildData(mySnapshotDir, "snapshot_2.png");
    final TheRGraphicsState.Listener listener = mock(TheRGraphicsState.Listener.class);

    myState.refresh(false);

    assertSizePreviousCurrentNext(2, false, null, true);

    myState.next();

    assertSizePreviousCurrentNext(2, false, file1, true);

    myState.addListener(listener);
    verifyZeroInteractions(listener);

    file1.delete(this);

    myState.refresh(false);

    verify(listener, times(1)).onUpdate();
    assertSizePreviousCurrentNext(1, false, file2, false);

    verifyNoMoreInteractions(listener);
  }

  public void testRemoveCurrentSnapshotWithPrevious() throws IOException {
    final VirtualFile file1 = createChildData(mySnapshotDir, "snapshot_1.png");
    final VirtualFile file2 = createChildData(mySnapshotDir, "snapshot_2.png");
    final TheRGraphicsState.Listener listener = mock(TheRGraphicsState.Listener.class);

    myState.refresh(false);

    assertSizePreviousCurrentNext(2, false, null, true);

    myState.next();

    assertSizePreviousCurrentNext(2, false, file1, true);

    myState.next();

    assertSizePreviousCurrentNext(2, true, file2, false);

    myState.addListener(listener);
    verifyZeroInteractions(listener);

    file2.delete(this);

    myState.refresh(false);

    verify(listener, times(1)).onUpdate();
    assertSizePreviousCurrentNext(1, false, file1, false);

    verifyNoMoreInteractions(listener);
  }

  public void testRemoveCurrentSnapshotWithoutNeighbours() throws IOException {
    final VirtualFile file = createChildData(mySnapshotDir, "snapshot_1.png");
    final TheRGraphicsState.Listener listener = mock(TheRGraphicsState.Listener.class);

    myState.refresh(false);

    assertSizePreviousCurrentNext(1, false, null, true);

    myState.next();

    assertSizePreviousCurrentNext(1, false, file, false);

    myState.addListener(listener);
    verifyZeroInteractions(listener);

    file.delete(this);

    myState.refresh(false);

    verify(listener, times(1)).onReset();
    assertEmpty();

    verifyNoMoreInteractions(listener);
  }

  public void testRenameSnapshot() throws IOException {
    final VirtualFile file = createChildData(mySnapshotDir, "snapshot_1.png");
    final TheRGraphicsState.Listener listener = mock(TheRGraphicsState.Listener.class);

    myState.refresh(false);

    assertSizePreviousCurrentNext(1, false, null, true);

    myState.next();

    assertSizePreviousCurrentNext(1, false, file, false);

    myState.addListener(listener);
    verifyZeroInteractions(listener);

    file.rename(this, "snapshot_2.png");

    myState.refresh(false);

    verify(listener, times(1)).onReset();
    assertEmpty();

    verifyNoMoreInteractions(listener);
  }

  public void testMoveSnapshot() throws IOException {
    final VirtualFile file = createChildData(mySnapshotDir, "snapshot_1.png");
    final TheRGraphicsState.Listener listener = mock(TheRGraphicsState.Listener.class);

    myState.refresh(false);

    assertSizePreviousCurrentNext(1, false, null, true);

    myState.next();

    assertSizePreviousCurrentNext(1, false, file, false);

    myState.addListener(listener);
    verifyZeroInteractions(listener);

    final VirtualFile anotherSnapshotDir = getVirtualFile(createTempDirectory(true));
    assert anotherSnapshotDir != null;

    file.move(this, anotherSnapshotDir);

    myState.refresh(false);

    verify(listener, times(1)).onReset();
    assertEmpty();

    verifyNoMoreInteractions(listener);
  }

  public void testCopySnapshot() throws IOException {
    final VirtualFile file = createChildData(mySnapshotDir, "snapshot_1.png");
    final TheRGraphicsState.Listener listener = mock(TheRGraphicsState.Listener.class);

    myState.refresh(false);

    assertSizePreviousCurrentNext(1, false, null, true);

    myState.next();

    assertSizePreviousCurrentNext(1, false, file, false);

    myState.addListener(listener);

    file.copy(this, mySnapshotDir, "snapshot_2.png");

    myState.refresh(false);

    assertSizePreviousCurrentNext(1, false, file, false);

    verifyZeroInteractions(listener);
  }

  public void testRemoveListener() throws IOException {
    final VirtualFile file = createChildData(mySnapshotDir, "snapshot_1.png");
    final TheRGraphicsState.Listener listener = mock(TheRGraphicsState.Listener.class);

    myState.refresh(false);

    myState.addListener(listener);
    verifyZeroInteractions(listener);

    assertSizePreviousCurrentNext(1, false, null, true);

    myState.next();

    verify(listener, times(1)).onUpdate();
    assertSizePreviousCurrentNext(1, false, file, false);

    myState.removeListener(listener);

    file.delete(this);

    myState.refresh(false);

    assertEmpty();

    verifyNoMoreInteractions(listener);
  }

  private void assertEmpty() throws FileNotFoundException {
    assertFalse(myState.hasNext());
    assertFalse(myState.hasPrevious());
    assertFalse(myState.hasCurrent());

    try {
      myState.current();

      fail("State successfully returns current");
    }
    catch (final NoSuchElementException ignore) {
    }

    assertEquals(0, myState.size());
  }

  private void assertSizePreviousCurrentNext(final int size,
                                             final boolean hasPrevious,
                                             @Nullable final VirtualFile current,
                                             final boolean hasNext) throws FileNotFoundException {
    assertEquals(hasNext, myState.hasNext());
    assertEquals(hasPrevious, myState.hasPrevious());

    if (current != null) {
      assertTrue(myState.hasCurrent());
      assertEquals(current, myState.current());
    }
    else {
      assertFalse(myState.hasCurrent());
    }

    assertEquals(size, myState.size());
  }
}
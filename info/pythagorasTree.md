# Pythagoras Tree

**Implementation of Pascal's Triangle as a custom view**

[See more](https://en.wikipedia.org/wiki/Pythagoras_tree_(fractal))

![Pythagoras Tree](/info/gifs/pythagorasTree.gif)

```xml
<org.redbyte.animatron.pythagoras.PythagorasTreeView
    android:id="@+id/tree"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:endColor="@color/colorRed"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintTop_toTopOf="parent"
    app:maxDepth="12"
    app:startColor="@color/purple_700" />
```
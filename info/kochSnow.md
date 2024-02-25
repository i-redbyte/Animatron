# Koch Snow

**Implementation of Koch's Snow as a custom view**

[See more](https://en.wikipedia.org/wiki/Koch_snowflake)

![Koch Snow](/info/gifs/kochSnow.gif)

```xml
    <org.redbyte.animatron.koch.KochSnowView
        android:id="@+id/kochSnow"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:endColor="@color/colorGo"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:level="7"
        app:startColor="@color/colorDarkGo" />
```
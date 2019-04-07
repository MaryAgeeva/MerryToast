# MerryToast

Small library for making customized toasts messages (Android)

How to install:
-------
  Library requires SDK ver. 19 and higher

Usage:
-------

With MerryToast you can create predefined type of Toasts, such as:
  * information:
 
```kt
  MerryToast.info(this, "Info toast")
```
  * warning:
 
```kt
  MerryToast.warn(this, "Warning toast")
```
  * success:
 
```kt
  MerryToast.success(this, "Success toast")
```

Also, you can create custom versions of Toasts using Builder:

```kt
  MerryToast.Builder(this)
        .image(R.drawable.icon_batman)
        .color(Colors.BLACK)
        .text("Batman toast")
        .shape(Shapes.OVAL)
        .fixedSize()
        .build()
```

Examples:
-------
[[https://github.com/MaryAgeeva/MerryToast/blob/master/screenshots/toast_batman.png]]

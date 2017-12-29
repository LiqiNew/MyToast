[![](https://jitpack.io/v/liqinew/mytoast.svg)](https://jitpack.io/#liqinew/mytoast)
[![](https://img.shields.io/badge/%E4%BD%9C%E8%80%85-%E6%9D%8E%E5%A5%87-orange.svg)](https://github.com/LiqiNew)
# MyToast
**在5.0以上系统上支持关闭系统通知权限后显示，支持自定toast视图显示。**<br><br>
**MyToast借鉴于： [EToast2](https://github.com/Blincheng/EToast2)**

#### MyToast的由来
当用户在5.0以上系统上关闭系统通知权限之后，系统Toast也会显示不出。让我们去跟踪一下Toast的源码，我们发现Toast其实是通过NotificationManagerService维护一个Toast队列。
然后通过队列去通知Toast中的客户端TN调用WindowManager去添加view显示。那么当用户关闭通知权限后自然也无法显示Toast了。

##### 解决思路
* 自己仿照系统的Toast然后用自己的消息队列来维护，让其不受NotificationManagerService影响。
* 通过WindowManager自己来写一个通知。
* 通过Dialog、PopupWindow来编写一个自定义通知。
* 通过直接去当前页面最外层content布局来添加View。

### 内部远程依赖Library（已经远程依赖的Library，切勿重复依赖。）

**BaseLogger：'com.github.liqinew:baselogger:V.1.0.0'**<br>

# 如何使用?
### Gradle远程依赖 ###
**1：在项目根目录build.gradley**	<br>
```gradle
allprojects {
　　repositories {
  　　//依赖仓库
　　　maven { url 'https://jitpack.io' }
　　}
}
```
**2：依赖MyToast**<br>
```gradle
compile 'com.github.liqinew:mytoast:V.1.0.0'
```
### 使用方式
**MyToast提供大量静态显示方法，请大家查阅MyToast对象API**
#### MyToast直接显示系统的布局的Toast(样例)
```java
MyToast.showShort(this, "Toast短时间弹出");

MyToast.showShort(this, "Toast长时间弹出");
```
#### MyToast显示自定义布局的Toast(样例)
```java
//由于视图控件ID不可控因素存在。要想在自定义toast里面去改变视图控件内容，那么请使用者自行针对自己定义的视图和MyToast再次封装。
View inflate = LayoutInflater.from(this).inflate(R.layout.xxxxx, null);

//此处只是调用底部显示自定义toast的方法，其他方法请自行进入MyToast对象里面去查看，里面注释很齐全的。
MyToast.putBottomDiyToastShort(this, inflate, 190, 0, 30);
```
### A P I
#### MyToast 操作APi
```java
    /**
     * 系统toast长时间显示
     *
     * @param context
     * @param contentInt
     */
MyToast.showLong(Context context, int contentInt);

    /**
     * 系统toast长时间显示
     *
     * @param context
     * @param contentStr
     */
MyToast.showLong(Context context, String contentStr);

    /**
     * 系统toast短时间显示
     *
     * @param context
     * @param contentInt
     */
MyToast.showShort(Context context, int contentInt);

    /**
     * 系统toast短时间显示
     *
     * @param context
     * @param contentStr
     */
MyToast.showShort(Context context, String contentStr);

    /**
     * 返回自定义toast
     *
     * @param context
     * @param view         布局View
     * @param toastTime    显示持续时长
     * @param transparency 透明度 0-255
     * @param gravity      视图显示位置
     * @param xOffset      X轴偏距
     * @param yOffset      Y轴偏距
     * @return
     */
MyToast.putDiyToast(Context context, View view, ToastTime toastTime,
                                    int transparency, int gravity, int xOffset, int yOffset);


    /**
     * 返回自定义toast居中不偏距长时间显示
     *
     * @param context
     * @param view         布局View
     * @param transparency 透明度 0-255
     * @return
     */
MyToast.putCenterDiyToastLong(Context context, View view, int transparency);
    /**
     * 返回自定义toast居中长时间显示
     *
     * @param context
     * @param view         布局View
     * @param transparency 透明度 0-255
     * @param xOffset      X轴偏距
     * @param yOffset      Y轴偏距
     * @return
     */
MyToast.putCenterDiyToastLong(Context context, View view, int transparency, int xOffset, int yOffset);


    /**
     * 返回自定义toast顶部不偏距长时间显示
     *
     * @param context
     * @param view         布局View
     * @param transparency 透明度 0-255
     * @return
     */
MyToast.putTopDiyToastLong(Context context, View view, int transparency);

    /**
     * 返回自定义toast顶部长时间显示
     *
     * @param context
     * @param view         布局View
     * @param transparency 透明度 0-255
     * @param xOffset      X轴偏距
     * @param yOffset      Y轴偏距
     * @return
     */
MyToast.putTopDiyToastLong(Context context, View view, int transparency, int xOffset, int yOffset);

    /**
     * 返回自定义toast底部不偏距长时间显示
     *
     * @param context
     * @param view         布局View
     * @param transparency 透明度 0-255
     * @return
     */
MyToast.putBottomDiyToastLong(Context context, View view, int transparency) ;

    /**
     * 返回自定义toast底部长时间显示
     *
     * @param context
     * @param view         布局View
     * @param transparency 透明度 0-255
     * @param xOffset      X轴偏距
     * @param yOffset      Y轴偏距
     * @return
     */
MyToast.putBottomDiyToastLong(Context context, View view, int transparency, int xOffset, int yOffset);
    /**
     * 返回自定义toast居中不偏距短时间显示
     *
     * @param context
     * @param view         布局View
     * @param transparency 透明度 0-255
     * @return
     */
MyToast.putCenterDiyToastShort(Context context, View view, int transparency);

    /**
     * 返回自定义toast居中短时间显示
     *
     * @param context
     * @param view         布局View
     * @param transparency 透明度 0-255
     * @param xOffset      X轴偏距
     * @param yOffset      Y轴偏距
     * @return
     */
MyToast.putCenterDiyToastShort(Context context, View view, int transparency, int xOffset, int yOffset);

    /**
     * 返回自定义toast顶部不偏距短时间显示
     *
     * @param context
     * @param view         布局View
     * @param transparency 透明度 0-255
     * @return
     */
MyToast.putTopDiyToastShort(Context context, View view, int transparency);

    /**
     * 返回自定义toast顶部短时间显示
     *
     * @param context
     * @param view         布局View
     * @param transparency 透明度 0-255
     * @param xOffset      X轴偏距
     * @param yOffset      Y轴偏距
     * @return
     */
MyToast.putTopDiyToastShort(Context context, View view, int transparency, int xOffset, int yOffset);

    /**
     * 返回自定义toast底部不偏距短时间显示
     *
     * @param context
     * @param view         布局View
     * @param transparency 透明度 0-255
     * @return
     */
MyToast.putBottomDiyToastShort(Context context, View view, int transparency);

    /**
     * 返回自定义toast底部短时间显示
     *
     * @param context
     * @param view         布局View
     * @param transparency 透明度 0-255
     * @param xOffset      X轴偏距
     * @param yOffset      Y轴偏距
     * @return
     */
MyToast.putBottomDiyToastShort(Context context, View view, int transparency, int xOffset, int yOffset);

    /**
     * 返回自定义toast短时间不偏距显示
     *
     * @param context
     * @param view         布局View
     * @param transparency 透明度 0-255
     * @param gravity      视图显示位置
     * @return
     */
MyToast.putDiyToastShort(Context context, View view, int transparency, int gravity);

    /**
     * 返回自定义toast短时间显示
     *
     * @param context
     * @param view         布局View
     * @param transparency 透明度 0-255
     * @param gravity      视图显示位置
     * @param xOffset      X轴偏距
     * @param yOffset      Y轴偏距
     * @return
     */
MyToast.putDiyToastShort(Context context, View view, int transparency, int gravity, int xOffset, int yOffset);

    /**
     * 返回自定义toast长时间不偏距显示
     *
     * @param context
     * @param view         布局View
     * @param transparency 透明度 0-255
     * @param gravity      视图显示位置
     * @return
     */
MyToast.putDiyToastLong(Context context, View view, int transparency, int gravity);

    /**
     * 返回自定义toast长时间显示
     *
     * @param context
     * @param view         布局View
     * @param transparency 透明度 0-255
     * @param gravity      视图显示位置
     * @param xOffset      X轴偏距
     * @param yOffset      Y轴偏距
     * @return
     */
MyToast.putDiyToastLong(Context context, View view, int transparency, int gravity, int xOffset, int yOffset);
```
**如果觉得MyToast对象提供的API不满足你的要求，你也可以针对com.liqi.toast.LToast和com.liqi.toast.Toast封装。<br><br>com.liqi.toast.LToast和com.liqi.toast.Toast的API请查阅源码。**

# DAY02
  
## Activity 生命周期
 
- **OnCreate** ：当Activity第一次被运行时调用此方法，可用于加载布局视图，获取控件命名空间等一些初始化工作。
- **onRestart** ：当Activity被重新启动的时候，调用此方法
- **onStart** ：表示Activity正在被启动，已经从不可见到可见状态（不是指用户可见，指Activity在后台运行，没有出现在前台），但还是无法与用户进行交互。
- **onResume** ：表Activity已经变为可见状态了，并且出现在前台工作了，也就是指用户可见了
- **onPause** ：表示Activity正在暂停，但Activity依然可见，可以执行一些轻量级操作，但一般不会进行太多操作，因为这样会影响用户体验。
- **onStop** ：表示Activity即将暂停，此时Activity工作在后台，已经不可见了，可以与onPause方法一样做一些轻量级操作，但依然不能太耗时。
- **onDestroy** ：表示活动即将被销毁。

## Android 四大组件

- **Activity** ：Activity是Android应用程序的一个核心组件，用于提供用户界面和交互。Activity代表了一个单独的屏幕，用户可以在其中执行操作。
- **Service** ：Service是一种后台运行的组件，用于执行长时间运行的任务或处理与用户界面无关的操作。Service可以在，后台运行，即使用户切换到其他应用或锁定屏幕，它仍然可以继续执行。
- **Broadcast Receiver** ：Broadcast Receiver (广播接收器)是一种用于接收和响应广播消息的组件。广播消息可以由系统、应用程序或其他组件发送，而Broadcast Receiver负责接收并处理这些消息。
- **Content Provider** ：Content Provider (内容提供者)是一种用于管理应用程序数据的组件。Content Provider允许应用程序共享数据给其他应用程序，并提供了一种标准化的接口，用于对数据进行查询、插入、更新和删除等操作。

## 截图在ScreenShoot文件夹下
**standard**
![standard](ScreenShot\standard.png)
**singleTask**
![singleTask](ScreenShot\singleTask.png)
**singleTop**
![singleTop](ScreenShot\singleTop.png)
**singleInstance**
![singleInstance](ScreenShot\singleInstance.png)
**Broadcast**
![Broadcast](ScreenShot\Broadcast.png)
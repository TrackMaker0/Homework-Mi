# DAY10

## ANR (Application Not Responding) 介绍

ANR，全称为 **Application Not Responding**，是 Android 系统中的一种机制，用来检测应用程序是否在一段时间内响应用户输入或其他重要操作。如果应用程序长时间没有响应，系统会显示一个对话框，提示用户该应用程序未响应，并提供“等待”或“关闭应用”的选项。

### ANR 触发条件

ANR 通常在以下情况下触发：

1. **主线程阻塞**：
    - **UI操作**：在主线程中执行耗时操作（如网络请求、数据库查询、大量计算等）会导致 UI 线程阻塞，无法及时处理用户输入或更新 UI。
    - **死锁**：如果主线程因死锁而无法继续执行，也会导致 ANR。

2. **BroadcastReceiver 超时**：
    - 如果在前台的 `BroadcastReceiver` 在 10 秒内没有完成执行，系统将触发 ANR。
    - 后台 `BroadcastReceiver` 的超时时间为 60 秒。

3. **Service 超时**：
    - 前台 Service：如果前台服务在 20 秒内没有完成启动或处理请求，系统将触发 ANR。
    - 后台 Service：后台服务的超时时间为 200 秒。

4. **ContentProvider 超时**：
    - 如果 ContentProvider 在 10 秒内没有返回结果，也会导致 ANR。

### ANR 预防方法

1. **避免在主线程中执行耗时操作**：
    - 使用 `AsyncTask`、`HandlerThread`、`IntentService` 或者其他异步处理机制。
    - 利用 `RxJava` 或 `Kotlin Coroutines` 来处理耗时任务。

2. **优化 UI 更新**：
    - 避免在 UI 线程中进行复杂的布局操作或动画。
    - 使用 `RecyclerView` 而不是 `ListView`，因为前者更高效。

3. **合理使用 BroadcastReceiver 和 Service**：
    - 确保 `BroadcastReceiver` 和 `Service` 执行的代码尽量简单快速。
    - 使用 `JobIntentService` 来处理长时间运行的后台任务。

4. **监控和调试**：
    - 使用 `StrictMode` 检测主线程上的违例操作。
    - 检查 `logcat` 中的 ANR 日志，通常在 `/data/anr/` 目录下生成 `traces.txt` 文件，可以分析导致 ANR 的调用栈。

## 内存泄露
内存泄漏指的是应用程序在运行过程中，由于程序的设计或代码的缺陷，某些不再需要使用的对象仍然被持有引用，无法被垃圾回收器 (GC) 回收，导致内存无法被释放，最终可能导致应用程序崩溃或性能下降。

### 内存泄漏的常见原因

1. **静态变量持有引用**：
    - 静态变量的生命周期与应用相同，如果静态变量持有对活动(Activity)或其他大对象的引用，那么这些对象在不需要时也无法被回收，从而导致内存泄漏。

2. **非静态内部类**：
    - 非静态内部类会持有其外部类的引用。如果这个内部类的实例被异步任务 (如 `Handler`、`AsyncTask`) 持有，可能会导致外部类（通常是 Activity 或 Fragment）在其生命周期结束后仍然无法被回收。

3. **Handler 导致的内存泄漏**：
    - 如果 `Handler` 在执行任务时，其 `Message` 或 `Runnable` 持有对外部对象的引用（如 Activity），这些对象会在 `Handler` 任务未完成时无法被回收，导致内存泄漏。

4. **单例模式**：
    - 单例模式会持有全局引用，如果在单例类中持有对 Activity、Context 等对象的引用，这些对象在单例的整个生命周期内都无法被回收，可能引发内存泄漏。

5. **未关闭的资源**：
    - 在使用 `Cursor`、`File`、`InputStream`、`OutputStream` 等资源时，未能及时关闭这些资源，可能导致内存泄漏。

6. **集合对象持有引用**：
    - 大型集合对象（如 `List`、`Map`）中存放了大量的 Activity 或 Fragment 实例，但这些实例没有被移除时，可能会导致内存泄漏。

### 预防内存泄漏的方法

1. **使用 Application Context**：
    - 对于需要长时间存在的对象，如单例或静态变量，使用 `Application` 的 `Context` 而不是 `Activity` 的 `Context`，因为前者的生命周期与整个应用程序一致。

2. **静态内部类**：
    - 将内部类声明为 `static`，从而避免隐式持有外部类的引用。对于非静态内部类，尽量使用弱引用 (`WeakReference`) 来持有外部类的引用。

3. **及时清理和释放资源**：
    - 在 `Activity` 或 `Fragment` 的 `onDestroy()` 中，及时移除 `Handler` 的消息队列、停止未完成的异步任务、清理大对象或集合，确保它们能够被回收。

4. **使用 LeakCanary**：
    - 引入开源工具 LeakCanary，可以帮助检测和诊断应用中的内存泄漏问题。

5. **弱引用和软引用**：
    - 使用 `WeakReference` 或 `SoftReference` 持有那些可能长时间持有但又不一定一直需要的对象，这样当系统内存紧张时，GC 可以优先回收这些对象。

6. **避免长生命周期对象持有短生命周期对象**：
    - 尽量避免长生命周期对象（如 Application 或单例）持有短生命周期对象（如 Activity、Fragment），以免短生命周期对象无法被及时回收。

### 内存泄漏的危害

- **内存消耗增加**：导致内存不断增长，可能引发 OutOfMemoryError (OOM) 错误。
- **应用崩溃**：当系统内存耗尽时，应用可能崩溃。
- **性能下降**：内存泄漏可能导致应用响应变慢，尤其在内存紧张的情况下。

避免和处理内存泄漏是开发稳定、高效 Android 应用的重要一环。通过良好的编码实践和工具检测，可以有效减少内存泄漏问题的发生。

## 查看源码的网站 [Android opengrok](androidxref.com)

## adb bugreport 查看bug日志

## 内存泄漏

### 四种引用

- **强引用**
- **软引用**
- **弱引用**
- **虚引用**

### 7种内存泄漏场景

内存泄漏是指应用程序不再需要的内存没有被及时释放，导致可用内存逐渐减少，最终可能导致应用崩溃或性能下降。
在 Java 和 Android 开发中，内存泄漏通常发生在以下几种场景中：

#### 1. 静态变量持有对象引用

静态变量的生命周期与应用程序相同，因此如果静态变量持有对某个对象的引用，该对象将无法被垃圾回收器回收，导致内存泄漏。

```java
public class MemoryLeakExample {
    private static List<Object> staticList = new ArrayList<>();

    public void addToList() {
        staticList.add(new Object());
    }
}
```
场景: `staticList` 是一个静态变量，它持有对象的引用，即使 `MemoryLeakExample` 对象已经不再使用，这些对象仍然无法被回收。

#### 2. 未正确移除的监听器和回调

在 Android 开发中，注册监听器和回调时，如果在不再需要时未及时注销，
这些监听器和回调会持有对 `Activity` 或 `Fragment` 的引用，导致内存泄漏。

```java
public class MyActivity extends Activity {
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      Button button = findViewById(R.id.my_button);
      button.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            // 执行一些操作
         }
      });
   }
}
```
场景: 如果未在 `onDestroy()` 方法中移除 `OnClickListener`，该 `Activity` 实例可能无法被回收，导致内存泄漏。

#### 3. 非静态内部类持有外部类引用

非静态内部类默认持有其外部类的引用，如果内部类对象的生命周期长于外部类（如 Activity），就会导致外部类不能被回收。

```java
public class MyActivity extends Activity {
    private class MyRunnable implements Runnable {
        @Override
        public void run() {
            // 执行一些操作
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyRunnable runnable = new MyRunnable();
        new Thread(runnable).start();
    }
}
```
场景: MyRunnable 是 MyActivity 的非静态内部类，它隐式地持有 MyActivity 的引用。如果这个 Runnable 被长时间使用，会导致 MyActivity 的实例无法被回收。

#### 4. Handler 导致的内存泄漏
在 Android 中，`Handler` 是常见的内存泄漏来源。如果 `Handler` 是在 `Activity` 或 `Fragment` 内部类中定义的，
并且消息没有在 `Activity` 销毁时及时处理或移除，`Handler` 会持有 `Activity` 的引用，导致内存泄漏。
```java
public class MyActivity extends Activity {
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // 处理消息
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 执行一些操作
            }
        }, 60000); // 1分钟后执行
    }
}
```
场景: 如果 `Activity` 在 `Runnable` 执行前被销毁，`Handler` 仍然持有对 `Activity` 的引用，导致内存泄漏。

#### 5. 资源未关闭

在 Android 开发中，某些资源（如数据库游标、文件流等）如果未正确关闭，也会导致内存泄漏。

```java
public void readFile() {
    FileInputStream fis = null;
    try {
        fis = new FileInputStream("file.txt");
        // 读取文件内容
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (fis != null) {
            try {
                fis.close(); // 确保文件流被关闭
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```
场景: 如果 `FileInputStream` 未在 `finally` 块中关闭，会导致文件句柄泄漏，进而导致内存泄漏。

#### 6. 长生命周期的对象持有短生命周期对象的引用

例如，`Application` 类通常在应用的整个生命周期中存在，如果它持有 `Activity` 的引用，
且该 `Activity` 的生命周期比 `Application` 短，则会导致 `Activity` 无法被回收。

```java
public class MyApplication extends Application {
    private Activity currentActivity;

    public void setCurrentActivity(Activity activity) {
        this.currentActivity = activity;
    }
}
```
场景: 如果 `MyApplication` 持有 `Activity` 的引用，而 `Activity` 被销毁时没有清空这个引用，就会导致 `Activity` 无法被回收。

#### 7. Bitmap 对象未及时回收

在 Android 中，`Bitmap` 对象会占用大量内存，如果未及时回收或复用，可能导致内存泄漏。

```java
public class MyActivity extends Activity {
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.large_image);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bitmap != null) {
            bitmap.recycle();
            bitmap = null;
        }
    }
}
```
场景: 如果 `Bitmap` 没有在 `onDestroy()` 中调用 `recycle()` 方法，`Bitmap` 对象将继续占用内存，可能导致内存泄漏。


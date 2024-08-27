# DAY09

## 用户权限

### 主要的用户权限
```xml
<!-- 访问网络权限 -->
<uses-permission android:name="android.permission.INTERNET" />

<!-- 访问网络状态 -->
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

<!-- 读取外部存储 -->
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />

<!-- 写入外部存储 -->
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

<!-- 访问设备位置（精确定位） -->
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />

<!-- 访问设备位置（粗略定位） -->
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />

<!-- 使用摄像头 -->
<uses-permission android:name="android.permission.CAMERA" />

<!-- 读取联系人 -->
<uses-permission android:name="android.permission.READ_CONTACTS" />

<!-- 发送短信 -->
<uses-permission android:name="android.permission.SEND_SMS" />

<!-- 录音 -->
<uses-permission android:name="android.permission.RECORD_AUDIO" />

<!-- 读取通话记录 -->
<uses-permission android:name="android.permission.READ_CALL_LOG" />

<!-- 获取应用安装列表 -->
<uses-permission android:name="android.permission.QUERY_ALL_PACKAGES" />

<!-- 访问手机状态 -->
<uses-permission android:name="android.permission.READ_PHONE_STATE" />

<!-- 使用指纹 -->
<uses-permission android:name="android.permission.USE_FINGERPRINT" />

<!-- 使用Biometric身份验证（从Android 9开始） -->
<uses-permission android:name="android.permission.USE_BIOMETRIC" />

<!-- 管理外部存储（从Android 11开始） -->
<uses-permission android:name="android.permission.MANAGE_EXTERNAL_STORAGE" />

<!-- 访问日历 -->
<uses-permission android:name="android.permission.READ_CALENDAR" />

<!-- 写入日历 -->
<uses-permission android:name="android.permission.WRITE_CALENDAR" />

<!-- 获取设备ID和通话信息 -->
<uses-permission android:name="android.permission.READ_PHONE_NUMBERS" />
```

### 请求权限的主要阶段

#### 1. **在 AndroidManifest.xml 中声明权限**
```xml
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```
#### 2. **检查和请求权限**
检查权限
```java
if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) 
        != PackageManager.PERMISSION_GRANTED) {
    // Permission is not granted
    ActivityCompat.requestPermissions(this, 
            new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
} else {
    // Permission has already been granted
}
```
请求权限
```java
ActivityCompat.requestPermissions(this, 
        new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
```
#### 3. **处理权限请求结果**
```java
@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                       @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    if (requestCode == REQUEST_CAMERA_PERMISSION) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permission was granted
            dispatchTakePictureIntent(); // Call method to take picture
        } else {
            // Permission was denied
            Toast.makeText(this, "Camera permission is required", Toast.LENGTH_SHORT).show();
        }
    }
}
```
#### 4. **在 AndroidManifest.xml 中声明权限**
如果权限请求失败或用户拒绝权限申请，可以在 onRequestPermissionsResult 方法中进行处理，并提供适当的用户反馈或引导用户到设置页面。
```java
@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                       @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    if (requestCode == REQUEST_CAMERA_PERMISSION) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permission was granted
            dispatchTakePictureIntent(); // Call method to take picture
        } else {
            // Permission was denied
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                // User has denied permission but has not checked "Don't ask again"
                Toast.makeText(this, "Camera permission is required for this feature.", Toast.LENGTH_LONG).show();
            } else {
                // User has denied permission and checked "Don't ask again"
                Toast.makeText(this, "Camera permission is required. Please enable it in app settings.", Toast.LENGTH_LONG).show();

                // Optionally, provide a button to open the app settings
                new AlertDialog.Builder(this)
                    .setTitle("Permission Required")
                    .setMessage("Camera permission is required for this feature. Please enable it in app settings.")
                    .setPositiveButton("Open Settings", (dialog, which) -> {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
            }
        }
    }
}
```

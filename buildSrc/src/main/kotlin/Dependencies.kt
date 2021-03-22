const val kotlinVersion = "1.4.2"
const val hilt_version = "2.33-beta"

object Dependencies {

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val materialComponents = "com.google.android.material:material:${Versions.materialDesignVersion}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constrainLayoutVersion}"
    const val daggerHilt = "com.google.dagger:hilt-android:$hilt_version"

    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navVersion}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navVersion}"
    const val navigationDynamic = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navVersion}"

    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}"

    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    const val glideAnnotation = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
    const val workerKtx = "androidx.work:work-runtime-ktx:${Versions.workVersion}"
    const val circleImageView = "de.hdodenhof:circleimageview:${Versions.circleImageViewVersion}"
    const val multiDex = "androidx.multidex:multidex:${Versions.multidexVersion}"
    const val sdpAndroid = "com.intuit.sdp:sdp-android:${Versions.sdpAndroidVersion}"
    const val sspAndroid = "com.intuit.ssp:ssp-android:${Versions.sdpAndroidVersion}"
}
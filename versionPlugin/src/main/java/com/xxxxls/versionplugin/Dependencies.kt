package com.xxxxls.versionplugin

/**
 * 依赖管理 (可以拆封为多个类)
 * @author Max
 * @date 2020/12/1.
 */

object Versions {
    const val appcompat = "1.3.0"
    const val coreKtx = "1.3.0"
    const val constraintLayout = "1.1.3"
    const val cardView = "1.0.0"
    const val recyclerView = "1.0.0"
    const val multiDex = "2.0.0"
    const val runner = "1.2.0"
    const val espresso = "3.2.0"
    const val annotation = "1.0.4"
    const val paging = "3.0.0-alpha01"
    const val work = "2.2.0"
    const val lifecycle = "2.2.0-rc02"
    const val room = "2.3.0-alpha01"
    const val hilt = "2.30-alpha"
    const val hiltViewModule = "1.0.0-alpha02"
    const val kotlin = "1.3.72"
    const val kotlinCoroutinesCore = "1.3.7"
    const val kotlinCoroutinesAndroid = "1.3.9"
    const val retrofit = "2.9.0"
    const val okHttp = "4.0.1"
    const val okHttpLogging = "4.9.0"
    const val fragment = "1.3.0-alpha06"
    const val activity = "1.3.0-alpha06"
    const val material = "1.2.0-alpha06"
    const val flexBox = "1.1.0"
    const val aRouterApi = "1.4.0"
    const val aRouterCompiler = "1.2.1"
    const val aRouterAnnotation = "1.0.4"
    const val rxjavaAdapter = "2.3.0"
    const val gson = "2.8.5"
    const val eventBus = "3.1.1"
    const val glide = "4.9.0"
    const val rxJava = "2.2.10"
    const val rxAndroid = "2.1.1"
    const val greenDao = "3.2.2"
    const val greenDaoUpgradeHelper = "v2.1.0"
    const val smartRefresh = "1.1.0"
    const val baseQuickAdapter = "2.9.47"
    const val immersionBar = "3.0.0"
    const val titleBar = "6.0"
    const val flycoTabLayout = "2.1.2@aar"
    const val rxFFmpeg = "2.1.0"
    const val progressManager = "1.5.0"
    const val pictureSelector = "v2.2.3-androidx"
    const val xLog = "1.6.1"
    const val junit = "4.12"
    const val coroutinesAdapter = "0.9.2"
    const val xsuper = "1.0.6"
}

object AndroidX {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val pagingRuntime = "androidx.paging:paging-runtime:${Versions.paging}"
    const val cardView = "androidx.cardview:cardview:${Versions.cardView}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val multiDex = "androidx.multidex:multidex:${Versions.multiDex}"
    const val annotation = "androidx.annotation:annotation:${Versions.annotation}"

    const val fragment = "androidx.fragment:fragment:${Versions.fragment}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val activity = "androidx.activity:activity-ktx:${Versions.activity}"
}

object Android {
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val runner = "androidx.test:runner:${Versions.runner}"
    const val junit = "junit:junit:${Versions.junit}"
    const val meteria = "com.google.android.material:material:${Versions.material}"
    const val flexBox = "com.google.android:flexbox:${Versions.flexBox}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val exoPlayer = "com.google.android.exoplayer:exoplayer:2.10.4"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.room}"
    const val compiler = "androidx.room:room-compiler:${Versions.room}"
    const val coroutines = "androidx.room:room-coroutines:${Versions.room}"
    const val rxJava = "androidx.room:room-rxjava2:${Versions.room}"
    const val testing = "androidx.room:room-testing:${Versions.room}"
    const val ktx = "androidx.room:room-ktx:${Versions.room}"
}

object Hilt {
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val androidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val viewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltViewModule}"
    const val compiler = "androidx.hilt:hilt-compiler:${Versions.hiltViewModule}"
}

object Work {
    const val workRuntime = "androidx.work:work-runtime:${Versions.work}"
    const val workTesting = "androidx.work:work-testing:${Versions.work}"
}

object Lifecycle {
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.lifecycle}"
    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycleJava8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
}

object ARouter {
    const val api = "com.alibaba:arouter-api:${Versions.aRouterApi}"
    const val compiler = "com.alibaba:arouter-compiler:${Versions.aRouterCompiler}"
    const val annotation = "com.alibaba:arouter-annotation:${Versions.aRouterAnnotation}"
}

object Kt {
    const val stdlibJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val stdlibJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutinesCore}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutinesAndroid}"
    const val test = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    const val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val mock = "com.squareup.retrofit2:retrofit-mock:${Versions.retrofit}"
    const val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLogging}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val rxjava = "com.squareup.retrofit2:adapter-rxjava:${Versions.rxjavaAdapter}"
    const val coroutinesAdapter =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.coroutinesAdapter}"

}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    const val integration = "com.github.bumptech.glide:okhttp3-integration:${Versions.glide}"
}


object Rxjava {
    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
}

object GreenDao {
    const val greenDao = "org.greenrobot:greendao:${Versions.greenDao}"
    const val upgradeHelper =
        "com.github.yuweiguocn:GreenDaoUpgradeHelper:${Versions.greenDaoUpgradeHelper}"
}

object Depend {
    const val eventBus = "org.greenrobot:eventbus:${Versions.eventBus}"

    const val smartRefresh = "com.scwang.smartrefresh:SmartRefreshLayout:${Versions.smartRefresh}"
    const val smartRefreshHeader =
        "com.scwang.smartrefresh:SmartRefreshHeader:${Versions.smartRefresh}"

    const val baseQuickAdapter =
        "com.github.CymChad:BaseRecyclerViewAdapterHelper:${Versions.baseQuickAdapter}"
    const val immersionBar = "com.gyf.immersionbar:immersionbar:${Versions.immersionBar}"
    const val flycoTabLayout = "com.flyco.tablayout:FlycoTabLayout_Lib:${Versions.flycoTabLayout}"
    const val titleBar = "com.hjq:titlebar:${Versions.titleBar}"
    const val progressmanager = "me.jessyan:progressmanager:${Versions.progressManager}"
    const val pictureSelector = "com.github.ltyhome:PictureSelector:${Versions.pictureSelector}"
    const val rxFFmpeg = "com.github.microshow:RxFFmpeg:${Versions.rxFFmpeg}"
    const val xLog = "com.elvishew:xlog:${Versions.xLog}"
}

object XSuper {
    const val utils = "com.github.xxxls.XSuper:utils:${Versions.xsuper}"
    const val image = "com.github.xxxls.XSuper:image:${Versions.xsuper}"
    const val core = "com.github.xxxls.XSuper:core:${Versions.xsuper}"
    const val status = "com.github.xxxls.XSuper:status:${Versions.xsuper}"
    const val titleBar = "com.github.xxxls.XSuper:titlebar:${Versions.xsuper}"
    const val logger = "com.github.xxxls.XSuper:logger:${Versions.xsuper}"
    const val adapter = "com.github.xxxls.XSuper:adapter:${Versions.xsuper}"
    const val widget = "com.github.xxxls.XSuper:widget:${Versions.xsuper}"
}

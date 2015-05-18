# kontakt.io SDK configuration
-keep class com.kontakt.sdk.android.** {
    *;
}
-keep public class com.kontakt.sdk.core.data.** {
    *;
}

# kontakt.io SDK configuration
# From the very beginning of Android SDK existence
# there was also a Core SDK for Java SE providing REST API interaction only.
# To some extent the Android SDK derives some classes from the Core SDK.
# However, at some point the Android-specific functionalities were introduced
# e.g. implementing Parcelable interface in models.
# For the packages below, both of which come from the Core SDK
# there are Android equivalents. Thus, we do not care
# about them and allow Proguard to take care of them during shrinking process.
-dontwarn com.kontakt.sdk.core.http.**
-dontwarn com.kontakt.sdk.core.data.changelog.**

# ButterKnife configuration copied from the official website http://jakewharton.github.io/butterknife/
-printmapping mapping.txt
-printseeds seeds.txt
-printusage unused.txt

-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewInjector { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

# =================================================

-ignorewarnings
-dontoptimize
-dontobfuscate
-dontskipnonpubliclibraryclasses

-ignorewarnings

-renamesourcefileattribute SourceFile

-keepattributes SourceFile,LineNumberTable,*Annotation*

-printmapping map.txt
-printseeds seed.txt

-keepclassmembers enum * { public static **[] values(); public static ** valueOf(java.lang.String); }

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.view.View { public <init>(android.content.Context); public <init>(android.content.Context, android.util.AttributeSet); public <init>(android.content.Context, android.util.AttributeSet, int); public void set*(...); }

-keepclassmembers class * extends android.app.Activity { public void *(android.view.View); }
-keepclassmembers class android.support.v4.app.Fragment { *** getActivity(); public *** onCreate(); public *** onCreateOptionsMenu(...); }

-keepclassmembers class * { @com.google.inject.Provides *; @android.test.suitebuilder.annotation.* *; void test*(...); }

-keep public class roboguice.**
-keep class com.google.inject.Binder
-keep class com.google.inject.Key
-keep class com.google.inject.Provider
-keep class com.google.inject.TypeLiteral

-keepclassmembers class * { @com.google.inject.Inject <init>(...); }
-keepclassmembers class com.google.inject.assistedinject.FactoryProvider2 { *; }
-keepclassmembers class com.google.** {
    private void finalizeReferent();
    protected void finalizeReferent();
    public void finalizeReferent();
    void finalizeReferent();

    private *** startFinalizer(java.lang.Class,java.lang.Object);
    protected *** startFinalizer(java.lang.Class,java.lang.Object);
    public *** startFinalizer(java.lang.Class,java.lang.Object);
    *** startFinalizer(java.lang.Class,java.lang.Object);
}

-keep class com.github.mobile.**
-keepclassmembers class com.github.mobile.** { *; }
-keepclassmembers class com.github.mobile.** { public <init>(...); }
-keep class org.eclipse.egit.github.**
-keepclassmembers class com.github.mobile.** { public <init>(...); }
-keepclassmembers class org.eclipse.egit.github.** { *; }

-keepclassmembers class * extends com.actionbarsherlock.ActionBarSherlock { public <init>(...); }

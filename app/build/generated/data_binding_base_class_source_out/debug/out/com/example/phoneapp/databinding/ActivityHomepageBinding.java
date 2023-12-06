// Generated by view binder compiler. Do not edit!
package com.example.phoneapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.phoneapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityHomepageBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final AppCompatButton generateReportButton;

  @NonNull
  public final ImageView imageView2;

  @NonNull
  public final AppCompatButton logOutButton;

  @NonNull
  public final AppCompatButton settingsButton;

  @NonNull
  public final AppCompatButton startEvaluationButton;

  private ActivityHomepageBinding(@NonNull RelativeLayout rootView,
      @NonNull AppCompatButton generateReportButton, @NonNull ImageView imageView2,
      @NonNull AppCompatButton logOutButton, @NonNull AppCompatButton settingsButton,
      @NonNull AppCompatButton startEvaluationButton) {
    this.rootView = rootView;
    this.generateReportButton = generateReportButton;
    this.imageView2 = imageView2;
    this.logOutButton = logOutButton;
    this.settingsButton = settingsButton;
    this.startEvaluationButton = startEvaluationButton;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityHomepageBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityHomepageBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_homepage, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityHomepageBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.generateReportButton;
      AppCompatButton generateReportButton = ViewBindings.findChildViewById(rootView, id);
      if (generateReportButton == null) {
        break missingId;
      }

      id = R.id.imageView2;
      ImageView imageView2 = ViewBindings.findChildViewById(rootView, id);
      if (imageView2 == null) {
        break missingId;
      }

      id = R.id.logOutButton;
      AppCompatButton logOutButton = ViewBindings.findChildViewById(rootView, id);
      if (logOutButton == null) {
        break missingId;
      }

      id = R.id.settingsButton;
      AppCompatButton settingsButton = ViewBindings.findChildViewById(rootView, id);
      if (settingsButton == null) {
        break missingId;
      }

      id = R.id.startEvaluationButton;
      AppCompatButton startEvaluationButton = ViewBindings.findChildViewById(rootView, id);
      if (startEvaluationButton == null) {
        break missingId;
      }

      return new ActivityHomepageBinding((RelativeLayout) rootView, generateReportButton,
          imageView2, logOutButton, settingsButton, startEvaluationButton);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
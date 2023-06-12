package com.example.lab05_1;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    private Button btnFadeInXml, btnFadeInCode, btnFadeOutXml, btnFadeOutCode, btnBlinkXml,
            btnBlinkCode, btnZoomInXml, btnZoomInCode, btnZoomOutXml, btnZoomOutCode, btnRotateXml,
            btnRotateCode, btnMoveXml, btnMoveCode, btnSlideUpXml, btnSlideUpCode, btnBounceXml,
            btnBounceCode, btnCombineXml, btnCombineCode;
    private ImageView ivUitLogo;
    private Animation.AnimationListener animationListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewsByIds();
        initVariables();
        handleClickAnimationXml (btnFadeInXml, R.anim.anim_fade_in);
        handleClickAnimationXml (btnFadeOutXml, R.anim.anim_fade_out);
        handleClickAnimationXml (btnBlinkXml, R.anim.anim_blink);
        handleClickAnimationXml (btnZoomInXml, R.anim.anim_zoom_in);
        handleClickAnimationXml (btnZoomOutXml, R.anim.anim_zoom_out);
        handleClickAnimationXml (btnRotateXml, R.anim.anim_rotate);
        handleClickAnimationXml (btnMoveXml, R.anim.anim_rotate);
        handleClickAnimationXml (btnSlideUpXml, R.anim.anim_slide_up);
        handleClickAnimationXml (btnBounceXml, R.anim.anim_bounce);
        handleClickAnimationXml (btnCombineXml, R.anim.anim_combine);
        //handle using code
        handleClickAnimationCode(btnFadeInCode,initFadeInAnimation());
        handleClickAnimationCode(btnFadeOutCode,initFadeOutAnimation());
        handleClickAnimationCode(btnBlinkCode,initBlinkAnimation());
        handleClickAnimationCode(btnZoomInCode,initZoomInAnimation());
        handleClickAnimationCode(btnZoomOutCode,initZoomOutAnimation());
        handleClickAnimationCode(btnRotateCode,initRotateAnimation());
        handleClickAnimationCode(btnMoveCode,initMoveAnimation());
        handleClickAnimationCode(btnSlideUpCode,initSlideUpAnimation());
        handleClickAnimationCode(btnBounceCode,initBounceAnimation());
        handleClickAnimationCode(btnCombineCode,initCombineAnimation());
        final Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_fade_in);
        animation.setAnimationListener(animationListener);
        btnFadeInXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivUitLogo.startAnimation(animation);
            }
        });
    }
    private void findViewsByIds() {
        ivUitLogo = (ImageView) findViewById(R.id.iv_uit_logo);
        btnFadeInXml = (Button) findViewById(R.id.btn_fade_in_xml);
        btnFadeInCode = (Button) findViewById(R.id.btn_fade_in_code);
        btnFadeOutXml = (Button) findViewById(R.id.btn_fade_out_xml);
        btnFadeOutCode = (Button) findViewById(R.id.btn_fade_out_code);
        btnBlinkXml = (Button) findViewById(R.id.btn_blink_xml);
        btnBlinkCode = (Button) findViewById(R.id.btn_blink_code);
        btnZoomInXml = (Button) findViewById(R.id.btn_zoom_in_xml);
        btnZoomInCode = (Button) findViewById(R.id.btn_zoom_in_code);
        btnZoomOutXml = (Button) findViewById(R.id.btn_zoom_out_xml);
        btnZoomOutCode = (Button) findViewById(R.id.btn_zoom_out_code);
        btnRotateXml = (Button) findViewById(R.id.btn_rotate_xml);
        btnRotateCode = (Button) findViewById(R.id.btn_rotate_code);
        btnMoveXml = (Button) findViewById(R.id.btn_move_xml);
        btnMoveCode = (Button) findViewById(R.id.btn_move_code);
        btnSlideUpXml = (Button) findViewById(R.id.btn_slide_up_xml);
        btnSlideUpCode = (Button) findViewById(R.id.btn_slide_up_code);
        btnBounceXml = (Button) findViewById(R.id.btn_bounce_xml);
        btnBounceCode = (Button) findViewById(R.id.btn_bounce_code);
        btnCombineXml = (Button) findViewById(R.id.btn_combine_xml);
        btnCombineCode = (Button) findViewById(R.id.btn_combine_code);
    }
    private void initVariables() {
        animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(getApplicationContext(), "Animation Stopped",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        };
    }
    private void handleClickAnimationCode(Button btn, final Animation animation) {

        // Handle onclickListenner to start animation
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivUitLogo.startAnimation(animation);
            }
        });
    }
    private Animation initFadeInAnimation(){
        AlphaAnimation animation = new AlphaAnimation(0f,1f);
        animation.setDuration(3000);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }
    private Animation initFadeOutAnimation(){
        AlphaAnimation animation = new AlphaAnimation(1f, 0f);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }
    private Animation initBlinkAnimation() {
        AlphaAnimation animation = new AlphaAnimation(0f, 1f);
        animation.setDuration(300);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setAnimationListener(animationListener);
        return animation;
    }
    private Animation initZoomInAnimation() {
        Animation animation = new ScaleAnimation(1f, 2f, 1f, 2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }
    private Animation initZoomOutAnimation() {
        Animation animation = new ScaleAnimation(1f, 0.5f, 1f, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }
    private Animation initRotateAnimation(){
        RotateAnimation animation = new RotateAnimation(0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(3000);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }
    private Animation initMoveAnimation(){
        Animation animation = new TranslateAnimation(0, 1000, 0, 0);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }
    private Animation initSlideUpAnimation(){
        Animation animation = new TranslateAnimation(0, 0, 0, -1000);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }
    private Animation initBounceAnimation(){
        AnimationSet animationSet = new AnimationSet(true);

        // Scale animation
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 1.2f, 1f, 0.8f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(300);
        scaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleAnimation.setRepeatCount(1);
        scaleAnimation.setRepeatMode(Animation.REVERSE);

        // Translate animation
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, -100);
        translateAnimation.setDuration(300);
        translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        translateAnimation.setRepeatCount(1);
        translateAnimation.setRepeatMode(Animation.REVERSE);

        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(translateAnimation);

        return animationSet;
    }
    private Animation initCombineAnimation(){
        AnimationSet animationSet = new AnimationSet(true);

        // Rotate animation
        RotateAnimation rotateAnimation = new RotateAnimation(0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

        // Scale animation
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 1.5f, 1f, 1.5f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(1000);
        scaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(scaleAnimation);

        return animationSet;
    }


    private void handleClickAnimationXml(Button btn, int animId)
    {
        Animation animationFade_In = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_fade_in);
        Animation animationFade_Out = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_fade_out);
        Animation animationBlink = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_blink);
        Animation animationZoom_In = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_zoom_in);
        Animation animationZoom_Out = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_zoom_out);
        Animation animationRotate = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_rotate);
        Animation animationMove = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_move);
        Animation animationSlide_Up= AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_slide_up);
        Animation animationBounce = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_bounce);
        Animation animationCombine = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_combine);
        btnFadeInXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivUitLogo.startAnimation(animationFade_In);
            }
        });
        btnFadeOutXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivUitLogo.startAnimation(animationFade_Out);
            }
        });
        btnBlinkXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivUitLogo.startAnimation(animationBlink);
            }
        });
        btnZoomInXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivUitLogo.startAnimation(animationZoom_In);
            }
        });
        btnZoomOutXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivUitLogo.startAnimation(animationZoom_Out);
            }
        });
        btnRotateXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivUitLogo.startAnimation(animationRotate);
            }
        });
        btnMoveXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivUitLogo.startAnimation(animationMove);
            }
        });
        btnSlideUpXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivUitLogo.startAnimation(animationSlide_Up);
            }
        });
        btnBounceXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivUitLogo.startAnimation(animationBounce);
            }
        });
        btnCombineXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivUitLogo.startAnimation(animationCombine);
            }
        });
    }
}
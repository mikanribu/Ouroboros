package epf.domethic.ouroboros.animations;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
 
public class CollapseAnimation extends Animation implements Animation.AnimationListener {
 
    private View view;
    private static int ANIMATION_DURATION; //The time the menu will have to expand
    private int LastWidth; 
    private int FromWidth; //The minimum size it will expand
    private int ToWidth; //The maximum size it will expand
    private static int STEP_SIZE=30;
    
    public CollapseAnimation(View v, int FromWidth, int ToWidth, int Duration) {
         
        this.view = v;
        LayoutParams lyp =  view.getLayoutParams(); //get the position of the menu layout
        ANIMATION_DURATION = 1;
        this.FromWidth = lyp.width;
        this.ToWidth = lyp.width;
        setDuration(ANIMATION_DURATION);
        setRepeatCount(20);
        setFillAfter(false); //the animation transformation is not applied after the animation is over. 
        setInterpolator(new AccelerateInterpolator()); // smooth the animation in time
        setAnimationListener(this);// check when we want to use the animation
    }
 
    public void onAnimationEnd(Animation anim) {
        // TODO Auto-generated method stub
    }
 
    public void onAnimationRepeat(Animation anim) {
        // TODO Auto-generated method stub
        LayoutParams lyp =  view.getLayoutParams();//position of menu layout
        lyp.width = lyp.width - ToWidth/20;//Decrease the size of the menu
        view.setLayoutParams(lyp);
    }
 
    public void onAnimationStart(Animation anim) {
       // TODO Auto-generated method stub
        LayoutParams lyp =  view.getLayoutParams();
        LastWidth = lyp.width;
   }

	
 
}
package epf.domethic.ouroboros.animations;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.Toast;
 
public class ExpandAnimation extends Animation implements Animation.AnimationListener {
    private View view; 
    private static int ANIMATION_DURATION; //The time the menu will have to expand
    private int LastWidth; 
    private int FromWidth; //The minimum size it will expand
    private int ToWidth; //The maximum size it will expand
    private static int STEP_SIZE=30;
    
    public ExpandAnimation(View v, int FromWidth, int ToWidth, int Duration) {
         
        this.view = v;
        ANIMATION_DURATION = 1;
        this.FromWidth = FromWidth;
        this.ToWidth = ToWidth;
        
        setDuration(ANIMATION_DURATION); 
        setRepeatCount(Duration); // the amount of time the animation will run//was setRepeatCount(20)
        setFillAfter(false); //the animation transformation is not applied after the animation is over. 
        setInterpolator(new AccelerateInterpolator()); // smooth the animation in time
        setAnimationListener(this); // check when we want to use the animation
    }
 
    public void onAnimationEnd(Animation anim) {
        // TODO Auto-generated method stub
         
    }
 
    public void onAnimationRepeat(Animation anim) {
        // TODO Auto-generated method stub
        LayoutParams lyp =  view.getLayoutParams(); //See the position of the menu layout at the moment 
        lyp.width = LastWidth +=ToWidth/20;//Increase the size of the layout to the wanted percentage of occupation
        view.setLayoutParams(lyp); 
    }
 
    public void onAnimationStart(Animation anim) {
        // TODO Auto-generated method stub
        LayoutParams lyp =  view.getLayoutParams(); //See the position of the menu layout at the moment 
        lyp.width = 0;
        view.setLayoutParams(lyp);
        LastWidth = 0;
    } 
}

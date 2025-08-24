package cn.tofucat.gdx.effect;

public class FadeEffect extends StageEffectAbstract implements StageEffect {

    @Override
    public void in(float duration) {
        alpha += delta / duration;
    }

    @Override
    public void out(float duration) {
        alpha -= delta / duration;
    }

}

package cn.tofucat.gdx.effect;

public interface StageEffect {

    float show(float delta, float inTime, float outTime);

    float show(float delta);

    void init();

    boolean complete();
}

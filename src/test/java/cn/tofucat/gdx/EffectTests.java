package cn.tofucat.gdx;

import cn.tofucat.gdx.effect.FadeEffect;
import cn.tofucat.gdx.effect.StageEffect;
import org.junit.jupiter.api.Test;

public class EffectTests {

    @Test
    public void fade() {
        StageEffect effect = new FadeEffect();
        for (int i = 0; i <= 60; i++) {
            float show = effect.show(0.1f, 3, 3);
            System.out.println(show);
        }
    }
}

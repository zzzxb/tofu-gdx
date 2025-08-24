package cn.tofucat.gdx.effect;

public abstract class StageEffectAbstract implements StageEffect {
    protected float alpha = 0;
    protected float delta = 0;
    protected boolean in = false;
    protected boolean out = false;

    protected abstract void in(float duration);

    protected abstract void out(float duration);

    @Override
    public float show(float delta, float inTime, float outTime) {
        this.delta = delta;
        if (!in) {
            in(inTime);
            alpha = Math.min(alpha, 1.0f);
            if (Float.compare(alpha, 1.0f) != -1.0f) in = true;
        } else if (!out) {
            out(outTime);
            alpha = Math.max(alpha, 0.0f);
            if (Float.compare(alpha, 0.0f) != 1.0f) out = true;
        }
        return alpha;
    }

    @Override
    public float show(float delta) {
        return show(delta, 1f, 1f);
    }

    @Override
    public void init() {
        alpha = 0;
        delta = 0;
        in = false;
        out = false;
    }

    @Override
    public boolean complete() {
        return in && out;
    }
}

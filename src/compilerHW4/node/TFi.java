/* This file was generated by SableCC (http://www.sablecc.org/). */

package compilerHW4.node;

import compilerHW4.analysis.*;

@SuppressWarnings("nls")
public final class TFi extends Token
{
    public TFi()
    {
        super.setText("end_consider");
    }

    public TFi(int line, int pos)
    {
        super.setText("end_consider");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TFi(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTFi(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TFi text.");
    }
}

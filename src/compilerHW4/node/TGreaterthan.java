/* This file was generated by SableCC (http://www.sablecc.org/). */

package compilerHW4.node;

import compilerHW4.analysis.*;

@SuppressWarnings("nls")
public final class TGreaterthan extends Token
{
    public TGreaterthan()
    {
        super.setText("lordTo");
    }

    public TGreaterthan(int line, int pos)
    {
        super.setText("lordTo");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TGreaterthan(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTGreaterthan(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TGreaterthan text.");
    }
}

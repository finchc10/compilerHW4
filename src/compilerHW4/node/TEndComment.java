/* This file was generated by SableCC (http://www.sablecc.org/). */

package lexing.node;

import lexing.analysis.*;

@SuppressWarnings("nls")
public final class TEndComment extends Token
{
    public TEndComment(String text)
    {
        setText(text);
    }

    public TEndComment(String text, int line, int pos)
    {
        setText(text);
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TEndComment(getText(), getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTEndComment(this);
    }
}

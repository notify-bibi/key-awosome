package de.uka.ilkd.key.java.recoderext;

import recoder.java.SourceVisitor;
import recoder.java.declaration.Modifier;


public class Model extends Modifier {

    /**
     *
     */
    private static final long serialVersionUID = -1997772798461795576L;


    public Model() {
    }


    protected Model(Model proto) {
        super(proto);
    }


    public Model deepClone() {
        return new Model(this);
    }


    public void accept(SourceVisitor v) {
    }
}

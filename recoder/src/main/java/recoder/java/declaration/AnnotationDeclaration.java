/*
 * Created on 11.03.2005
 *
 * This file is part of the RECODER library and protected by the LGPL.
 */
package recoder.java.declaration;

import recoder.java.Identifier;
import recoder.java.SourceVisitor;
import recoder.list.generic.ASTList;

/**
 * @author gutzmann
 */
public class AnnotationDeclaration extends InterfaceDeclaration {
    /**
     * serialization id
     */
    private static final long serialVersionUID = -5764583750285766921L;

    /**
     *
     */
    public AnnotationDeclaration() {
        super();
    }

    public AnnotationDeclaration(ASTList<DeclarationSpecifier> modifiers, Identifier name,
            ASTList<MemberDeclaration> members) {
        super(modifiers, name, null, members);
    }

    /**
     * @param proto
     */
    protected AnnotationDeclaration(AnnotationDeclaration proto) {
        super(proto);
    }

    public boolean isInterface() {
        return true;
    }

    public boolean isOrdinaryInterface() {
        return false;
    }

    public boolean isAnnotationType() {
        return true;
    }

    public boolean isEnumType() {
        return false;
    }

    public boolean isOrdinaryClass() {
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see recoder.java.SourceElement#accept(recoder.java.SourceVisitor)
     */
    public void accept(SourceVisitor v) {
        v.visitAnnotationDeclaration(this);
    }

    /*
     * (non-Javadoc)
     *
     * @see recoder.java.SourceElement#deepClone()
     */
    public AnnotationDeclaration deepClone() {
        return new AnnotationDeclaration(this);
    }
}

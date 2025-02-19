package de.uka.ilkd.key.speclang.translation;

import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nonnull;

import de.uka.ilkd.key.java.Position;
import de.uka.ilkd.key.speclang.PositionedString;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.text.MessageFormat.format;


/**
 * Factory for exception during parsing of specification languages.
 * <p>
 * This class produces exception with position information. For this, the class can be constructed
 * from antlr3.
 */
public class SLExceptionFactory {
    public static final Logger LOGGER = LoggerFactory.getLogger(SLExceptionFactory.class);

    private String fileName;
    private final int offsetLine, offsetColumn;
    /**
     * line, 1-based
     */
    private int line;
    /**
     * column, 0-based
     */
    private int column;

    private final List<PositionedString> warnings = new LinkedList<>();

    // -------------------------------------------------------------------------
    // constructors
    // -------------------------------------------------------------------------

    public SLExceptionFactory(@Nonnull Parser parser, String fileName, Position offsetPos) {
        this.line = parser.getInputStream().LT(1).getLine();
        this.column = parser.getInputStream().LT(1).getCharPositionInLine();
        this.fileName = fileName;
        this.offsetColumn = offsetPos.column();
        this.offsetLine = offsetPos.line();
    }

    public SLExceptionFactory(String fileName, int line, int column) {
        this.fileName = fileName;
        this.offsetColumn = column;
        this.offsetLine = line;
        this.line = 0;
        this.column = 0;
    }

    public SLExceptionFactory updatePosition(Token start) {
        fileName = start.getTokenSource().getSourceName();
        line = start.getLine();
        column = start.getCharPositionInLine();
        return this;
    }

    // -------------------------------------------------------------------------
    // internal methods
    // -------------------------------------------------------------------------
    private Position createAbsolutePosition(int relativeLine, int relativeColumn) {
        int absoluteLine = offsetLine + relativeLine - 1;
        int absoluteColumn = (relativeLine == 1 ? offsetColumn : 1) + relativeColumn;
        return Position.fromOneZeroBased(absoluteLine, absoluteColumn);
    }

    // -------------------------------------------------------------------------
    // public interface
    // -------------------------------------------------------------------------

    // region warnings

    /**
     * This is used for features without semantics such as labels or annotations.
     *
     * @author bruns
     * @since 1.7.2178
     */
    public void addIgnoreWarning(String feature) {
        String msg = feature + " is not supported and has been silently ignored.";
        addWarning(msg);
    }

    public void addIgnoreWarning(String feature, Token t) {
        String msg = feature + " is not supported and has been silently ignored.";
        addWarning(msg, t);
    }

    public void raiseNotSupported(String feature) {
        addWarning(feature + " not supported");
    }

    /**
     * Used for features with semantics (currently) not supported in KeY/DL.
     */
    public void addUnderspecifiedWarning(String feature) {
        String msg = format(
            "{0} is not supported and translated to an underspecified term or formula.", feature);
        addWarning(msg);
    }

    public void addUnderspecifiedWarning(Token t) {
        String msg =
            format("{0} is not supported and translated to an underspecified term or formula.",
                t.getText());
        addWarning(msg, t);
    }

    public void addDeprecatedWarning(String feature) {
        addWarning("deprecated syntax: " + feature);
    }

    public void addWarning(String msg) {
        LOGGER.debug("JML translator warning: " + msg);
        warnings.add(new PositionedString(msg, ""));
    }

    public void addWarning(String msg, Token t) {
        LOGGER.debug("JML translator warning: " + msg);
        warnings.add(createPositionedString(msg, t));
    }

    public List<PositionedString> getWarnings() {
        return warnings;
    }
    // endregion

    public PositionedString createPositionedString(String msg, Token t) {
        return new PositionedString(msg, fileName,
            createAbsolutePosition(t.getLine(), t.getCharPositionInLine()));
    }

    /**
     * Creates an SLTranslationException with current absolute position information.
     */
    public SLTranslationException createException(String message) {
        return new SLTranslationException(message, fileName,
            createAbsolutePosition(this.line, this.column));
    }


    /**
     * Creates an SLTranslationException with the position information of the passed token.
     */
    public SLTranslationException createException(String message, Token t) {
        return new SLTranslationException(message, fileName,
            createAbsolutePosition(t.getLine(), t.getCharPositionInLine()));
    }

    /**
     * Creates an SLTranslationException with current absolute position information.
     */
    public SLTranslationException createException(String message, Throwable cause) {
        SLTranslationException result = createException(message);
        result.initCause(cause);
        return result;
    }

    public RuntimeException createException0(String s) {
        return new RuntimeException(createException(s));
    }

    public RuntimeException createException0(String s, Throwable t) {
        return new RuntimeException(createException(s, t));
    }

    public RuntimeException createException0(String s, Token t) {
        return new RuntimeException(createException(s, t));
    }

    public RuntimeException createException0(String s, Token t, Throwable cause) {
        return new RuntimeException(createException(s, t, cause));
    }

    /**
     * Creates an SLTranslationException with the position information of the passed token.
     *
     * @param cause the exception which causes the new exception to be created.
     */
    public SLTranslationException createException(String message, Token t, Throwable cause) {
        SLTranslationException result = createException(message, t);
        result.initCause(cause);
        return result;
    }

    /**
     * Creates an SLWarningException with current absolute position information.
     */
    public SLTranslationException createWarningException(String message) {
        return new SLWarningException(message, fileName,
            createAbsolutePosition(this.line, this.column));
    }
}

package Utils.error;
import Utils.position;

public class semanticError extends error {

    public semanticError(String msg, position pos) {
        super("Semantic Error: " + msg, pos);
    }

}
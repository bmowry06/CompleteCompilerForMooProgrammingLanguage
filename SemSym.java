import java.util.*;

/**
 * The Sym class defines a symbol-table entry. 
 * Each Sym contains a type (a Type).
 */
public class SemSym {
    private Type type;
	public int offSet = 0;
    public int structOffSet;
	public boolean isGlobal;
	public int size;
	
    public SemSym(Type type) {
        this.type = type;
		this.offSet = 0;
		this.structOffSet = 0;
		this.isGlobal = false;
		this.size = 0;
    }
    
    public Type getType() {
        return type;
    }
    
    public String toString() {
        return type.toString();
    }
}

/**
 * The FnSym class is a subclass of the Sym class just for functions.
 * The returnType field holds the return type and there are fields to hold
 * information about the parameters.
 */
class FnSym extends SemSym {
    // new fields
    private Type returnType;
    private int numParams;
    private List<Type> paramTypes;
	
	private int offSet = 0;
	public int localSpace;
	public int formalSpace;
	
    
    public FnSym(Type type, int numparams) {
        super(new FnType());
        returnType = type;
        numParams = numparams;
		localSpace = 0;
		formalSpace = 0;
    }

    public void addFormals(List<Type> L) {
        paramTypes = L;
    }
    
    public Type getReturnType() {
        return returnType;
    }

    public int getNumParams() {
        return numParams;
    }

    public List<Type> getParamTypes() {
        return paramTypes;
    }

    public String toString() {
        // make list of formals
        String str = "";
        boolean notfirst = false;
        for (Type type : paramTypes) {
            if (notfirst)
                str += ",";
            else
                notfirst = true;
            str += type.toString();
        }

        str += "->" + returnType.toString();
        return str;
    }
}

/**
 * The StructSym class is a subclass of the Sym class just for variables 
 * declared to be a struct type. 
 * Each StructSym contains a symbol table to hold information about its 
 * fields.
 */
class StructSym extends SemSym {
    // new fields
    private IdNode structType;  // name of the struct type
    
    public StructSym(IdNode id) {
        super(new StructType(id));
        structType = id;
    }

    public IdNode getStructType() {
        return structType;
    }    
}

/**
 * The StructDefSym class is a subclass of the Sym class just for the 
 * definition of a struct type. 
 * Each StructDefSym contains a symbol table to hold information about its 
 * fields.
 */
class StructDefSym extends SemSym {
    // new fields
    private SymTable symTab;
	public int structSize;
    
    public StructDefSym(SymTable table) {
        super(new StructDefType());
        symTab = table;
		structSize = 0;
    }

    public SymTable getSymTable() {
        return symTab;
    }
}

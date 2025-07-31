import java.util.*;

class Hash {
    private int SDBMHash(String str, int num_buckets) {
        int hash = 0;
        int len = str.length();
        for (int i = 0; i < len; i++)
        {
            hash = Math.abs((str.charAt(i)) + (hash << 6) + (hash << 16) - hash);
        }
        return hash % num_buckets;
    }

    public int computeHash(String str, int num_buckets) {
        return SDBMHash(str, num_buckets);
    }
}

class FunctionInfo {
    private String returnType;
    private List<String> parameterTypes;
    private List<String> parameterNames;
    
    public FunctionInfo(String returnType) {
        this.returnType = returnType;
        this.parameterTypes = new ArrayList<>();
        this.parameterNames = new ArrayList<>();
    }
    
    public void addParameter(String type, String name) {
        parameterTypes.add(type);
        parameterNames.add(name);
    }
    
    // Getters and other methods
    public String getReturnType() { return returnType; }
    public List<String> getParameterTypes() { return parameterTypes; }
    public List<String> getParameterNames() { return parameterNames; }
    public int getParameterCount() { 
        return parameterTypes.size(); 
    }
}


class SymbolInfo {
    private int isGlobal = -1;
    private int stackOffset = -1;

    private String name;
    private String type;
    public SymbolInfo next;
    private boolean isFunction;
    private FunctionInfo functionInfo; // null for non-functions
    private boolean isFunctionParam;
    private boolean isArray;
    private int arraySize;
    private String dataType; // int, float etc

    public SymbolInfo(String name, String type, SymbolInfo next) {
        this.name = name;
        this.type = type;
        this.next = next;
        this.isFunction = false;
        this.functionInfo = null;
        this.isArray = false;
        this.arraySize = -1; 
    }
    
    public SymbolInfo(String name, String type) {
        this(name, type, null); // Calls the main constructor
    }

    public SymbolInfo(String name, String returnType, List<String> paramTypes, List<String> paramNames) {
        this.name = name;
        // this.type = "FUNCTION";
        this.type = "ID";
        this.isFunction = true;
        this.functionInfo = new FunctionInfo(returnType);
        this.isArray = false;
        this.arraySize = -1;

            

        if (paramTypes != null) {
            for (int i = 0; i < paramTypes.size(); i++) {
                this.functionInfo.addParameter(paramTypes.get(i), paramNames.get(i));
            }
        }
    }

    // Getters and setters
    public boolean isFunction() { return isFunction; }
    public FunctionInfo getFunctionInfo() { return functionInfo; }
    public boolean isArray() { return isArray; }
    public void setArray(int size) { 
        this.isArray = true; 
        this.arraySize = size;
    }

    public void setFunctionParam(boolean isFunctionParam) {
        this.isFunctionParam = isFunctionParam;
    }

    public boolean isFunctionParam() { return isFunctionParam; }

    public int getStackOffset() { return stackOffset; }
    public void setStackOffset(int stackOffset) { this.stackOffset = stackOffset; }
    public boolean isGlobal() { return this.isGlobal == 1; }
    public void setGlobal() { this.isGlobal = 1; }

    public int getArraySize() { return this.arraySize; }

    public void setDataType(String dataType) {
        this.dataType = dataType + (isArray ? "[]" : "");
    }

    public String getDataType() {
        return this.dataType == null ? "" : this.dataType.toLowerCase();
    }

    public void printSymbolInfo() {
        System.out.println(this.name + " " + this.type);
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getType()
    {
        return type;
    }
    
    public void setType(String type)
    {
        this.type = type;
    }
}

class ScopeTable
{
	private int numberOfBuckets;
    int numberOfChildren;
    private String id;
    private ArrayList<SymbolInfo>[] hashTable;
    // private SymbolInfo** hash_table; // array of pointers
	public ScopeTable parentScope;
    private static Hash hash = new Hash();

	public ScopeTable(int bucketCount, ScopeTable parentScope, boolean printMessage) {
        this.numberOfChildren = 0;
    	this.numberOfBuckets = bucketCount;
        this.parentScope = parentScope;
        this.hashTable = new ArrayList[numberOfBuckets];
        
        for (int i = 0; i < numberOfBuckets; i++) {
            hashTable[i] = new ArrayList<>();
        }
		        
        if(parentScope == null)
		{
			this.id = "1";     // for the first scope 
		}
		else
		{
			this.id = this.parentScope.getID() + "." + (this.parentScope.getNumberOfChildren());
		}

        if (printMessage) {
            System.out.println("\tScopeTable# " + id + " created");
        }
        
	}

    public ScopeTable(int bucketCount) {
    	this(bucketCount, null, true);
	}
	
    public int getBucketIndex (String str) {
		return hash.computeHash(str, this.numberOfBuckets);
	}

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void incrementNumberOfChildren() {
        numberOfChildren++;
    }

	public String getID() { return this.id; }

	public int getBucketCount() {
		return numberOfBuckets;
	}

	public int getIndexInChain(String name)
	{
		int index = getBucketIndex(name); // determine which bucket to search

		ArrayList<SymbolInfo> chain = hashTable[index];
        
        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i).getName().equals(name)) {
                return i;
            }
        }

		return -1; // Not found
	}

	public void printScopeTable() {
		final String indent = "\t";
        System.out.println(indent + "ScopeTable# " + id);
        
        for (int bucket = 0; bucket < numberOfBuckets; bucket++) {
            System.out.print(indent + (bucket) + "-->");
            
            ArrayList<SymbolInfo> chain = hashTable[bucket];
            for (SymbolInfo entry : chain) {
                System.out.print(" <" + entry.getName() + "," + entry.getType() + ">");
            }
            System.out.println();
        }
	}

    public String generateScopeTableString() {
        final String indent = "\t";
        StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append(indent).append("ScopeTable# ").append(id).append("\n");

    for (int bucket = 0; bucket < numberOfBuckets; bucket++) {
        stringBuilder.append(indent).append(bucket).append("-->");

        ArrayList<SymbolInfo> chain = hashTable[bucket];
        for (SymbolInfo entry : chain) {
            stringBuilder.append(" <").append(entry.getName()).append(",").append(entry.getType()).append(">");
        }
        stringBuilder.append("\n");
    }

        return stringBuilder.toString();
    }


	public SymbolInfo lookup(String name, boolean printDetails) 
	{
		int index = getBucketIndex(name);
        ArrayList<SymbolInfo> chain = hashTable[index];
        
        for (int position = 0; position < chain.size(); position++) {
            SymbolInfo curr = chain.get(position);
            if (curr.getName().equals(name)) {
                if (printDetails) {
                    System.out.println("'" + name + "' found in ScopeTable# " + this.id +
                            " at position " + (index + 1) + ", " + (position + 1));
                }
                return curr;
            }
        }
        return null;
	}

    public SymbolInfo lookup(String name) {
        return lookup(name, false);
    }

	public boolean insertSymbol(String name, String type, boolean printDetails)
	{
		if (lookup(name) != null) {
            return false;  // Symbol already exists
        }
        
        int bucket = getBucketIndex(name);
        SymbolInfo newEntry = new SymbolInfo(name, type);
        ArrayList<SymbolInfo> chain = hashTable[bucket];
        
        chain.add(newEntry);
        int position = chain.size();  // Position is 1-based
        
        if (printDetails) {
            System.out.println("Inserted in ScopeTable# " + id + 
                    " at position " + (bucket + 1) + ", " + position);
        }
        return true;
	}

    public boolean insertSymbol(String name, String type) {
        return insertSymbol(name, type, false);
    }

    public boolean insertFunction(String name, String returnType, List<String> paramTypes, List<String> paramNames, boolean printDetails) {
        if (lookup(name) != null) {
            return false;  // Symbol already exists
        }
        
        int bucket = getBucketIndex(name);
        SymbolInfo newEntry = new SymbolInfo(name, returnType, paramTypes, paramNames);
        ArrayList<SymbolInfo> chain = hashTable[bucket];
        
        chain.add(newEntry);
        int position = chain.size();  // Position is 1-based
        
        if (printDetails) {
            System.out.println("Inserted in ScopeTable# " + id + 
                    " at position " + (bucket + 1) + ", " + position);
        }
        return true;
    }

    public boolean insertArray(String name, String type, int size, boolean printDetails) {
        if (lookup(name) != null) {
            return false;  // Symbol already exists
        }
        
        int bucket = getBucketIndex(name);
        SymbolInfo newEntry = new SymbolInfo(name, type);
        newEntry.setArray(size);
        ArrayList<SymbolInfo> chain = hashTable[bucket];
        
        chain.add(newEntry);
        int position = chain.size();  // Position is 1-based
        
        if (printDetails) {
            System.out.println("Inserted in ScopeTable# " + id + 
                    " at position " + (bucket + 1) + ", " + position);
        }
        return true;

    }

	public boolean deleteSymbol(String name, boolean showOutput) 
	{
		int bucket = getBucketIndex(name);
        ArrayList<SymbolInfo> chain = hashTable[bucket];
        
        for (int position = 0; position < chain.size(); position++) {
            SymbolInfo curr = chain.get(position);
            if (curr.getName().equals(name)) {
                chain.remove(position);
                if (showOutput) {
                    System.out.println("Deleted '" + name + "' from ScopeTable# " + id +
                            " at position " + (bucket + 1) + ", " + (position + 1));
                }
                return true;
            }
        }
        return false;
    }

    public boolean deleteSymbol(String name) {
        return deleteSymbol(name, false);
    }
}

public class SymbolTable {
    private int numberOfBuckets;
    private ScopeTable currentScopeTable;

    public SymbolTable(int n, boolean printMessage) {
        this.numberOfBuckets = n;
        // enterScope(printMessage);
        this.currentScopeTable = new ScopeTable(n, null, printMessage); // added later
    }

    public SymbolTable(int n) {
        this(n, false);
    }

    public ScopeTable getCurrentScopeTable() {
        return currentScopeTable;
    }

    public boolean areWeAtTheRootScopeTable() {
        return currentScopeTable.parentScope == null;
    }

    public boolean insertIntoCurrentScope(String name, String type, boolean printMessage) {
        boolean exists = currentScopeTable.lookup(name) != null;
        if (exists) {
            if (printMessage) {
                System.out.println("'" + name + "' already exists in the current ScopeTable");
            }
            return false;
        }
        return currentScopeTable.insertSymbol(name, type, printMessage);
    }

    public boolean insertIntoCurrentScope(String name, String type) {
        return insertIntoCurrentScope(name, type, false);
    }

    public boolean insertFunction(String name, String returnType, List<String> paramTypes, List<String> paramNames, boolean printMessage) {
        boolean exists = currentScopeTable.lookup(name) != null;
        if (exists) {
            if (printMessage) {
                System.out.println("'" + name + "' already exists in the current ScopeTable");
            }
            return false;
        }
        return currentScopeTable.insertFunction(name, returnType, paramTypes, paramNames, printMessage);
    }

    public boolean insertArray(String name, String type, int size, boolean printMessage) {
        boolean exists = currentScopeTable.lookup(name) != null;
        if (exists) {
            if (printMessage) {
                System.out.println("'" + name + "' already exists in the current ScopeTable");
            }
            return false;
        }

        return currentScopeTable.insertArray(name, type, size, printMessage);
    }

    public boolean removeFromCurrentScope(String name, boolean printMessage) {
        boolean result = currentScopeTable.deleteSymbol(name, printMessage);
        if (!result && printMessage) {
            System.out.println("Not found in the current ScopeTable");
        }
        return result;
    }

    public boolean removeFromCurrentScope(String name) {
        return removeFromCurrentScope(name, false);
    }

    public SymbolInfo lookup(String name, boolean printMessage) {
        ScopeTable curr = currentScopeTable;
        while (curr != null) {
            SymbolInfo found = curr.lookup(name, printMessage);
            if (found != null) {
                return found;
            }
            curr = curr.parentScope;
        }
        if (printMessage) {
            System.out.println("'" + name + "' not found in any of the ScopeTables");
        }
        return null;
    }

    public SymbolInfo lookupInCurrentScope(String name, boolean printMessage) {
        SymbolInfo found = currentScopeTable.lookup(name, printMessage);
        if (found == null && printMessage) {
            System.out.println("'" + name + "' not found in the current ScopeTable");
        }
        return found;
    }


    public SymbolInfo lookup(String name) {
        return lookup(name, false);
    }

    public ScopeTable getScopeTableOfLookup(String name) {
        ScopeTable curr = currentScopeTable;
        while (curr != null) {
            if (curr.lookup(name) != null) {
                return curr;
            }
            curr = curr.parentScope;
        }
        return null;
    }

    public void enterScope(boolean printMessage) {
        if (currentScopeTable != null) {
            currentScopeTable.incrementNumberOfChildren();
        }
        currentScopeTable = new ScopeTable(numberOfBuckets, currentScopeTable, printMessage);
    }

    public void enterScope() {
        enterScope(false);
    }

    public void exitScope(boolean printMessage) {
        if (currentScopeTable == null || currentScopeTable.parentScope == null) {
            return;
        }
        // System.out.println("exit");
        System.out.println("ScopeTable# " + currentScopeTable.getID() + " removed");
        ScopeTable old = currentScopeTable;
        currentScopeTable = old.parentScope;
        old.parentScope = null;
    }

    public void exitScope() {
        exitScope(false);
    }

    public void printCurrentScopeTable() {
        if (currentScopeTable != null) {
            currentScopeTable.printScopeTable();
        }
    }

    public void printAllScopeTables() {
        ScopeTable curr = currentScopeTable;
        while (curr != null) {
            curr.printScopeTable();
            System.out.print("\t");
            curr = curr.parentScope;
        }
    }

    public String generateAllScopeTableString() {
        StringBuilder result = new StringBuilder();
        ScopeTable curr = currentScopeTable;
        while (curr != null) {
            result.append(curr.generateScopeTableString());
            curr = curr.parentScope;
        }
        return result.toString();
    }
}                           
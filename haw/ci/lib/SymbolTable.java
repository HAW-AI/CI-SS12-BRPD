package haw.ci.lib;

/*
 * As seen on:
 * https://github.com/HAW-AI/CI-SS12-FSTT/blob/master/src/haw/ai/ci/SymbolTable.java
 */


import haw.ci.lib.descriptor.Descriptor;
import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
	private Map<String, Integer> addressMap = new HashMap<String, Integer>();
	private Map<String, Descriptor> descriptorMap = new HashMap<String,Descriptor>();
	private Map<String, Integer> constMap = new HashMap<String,Integer>();
	private int currentAddress = 0;
	private SymbolTable parentTable;
	
	
	public SymbolTable(){
		this.parentTable = null;
	}
	
	public SymbolTable(SymbolTable table){
		this.parentTable = table;
	}
	
	public static SymbolTable createSymbolTable(){
		return new SymbolTable();
	}
	
	public void declareConst(String ident, int value){
		constMap.put(ident, value);
	}
	
	public int getConstVal(String ident){
		return constMap.get(ident);
	}
	
	public boolean isLocal(String ident) {
		return addressMap.containsKey(ident);
	}
	

	
	public void declare(String ident, Descriptor descriptor) {
		if(!(addressMap.containsKey(ident))){
			descriptorMap.put(ident, descriptor);
			addressMap.put(ident, currentAddress);
			if(descriptor == null){
				System.out.println("---- compile Error-----\n Variable = " + ident);
			}
			currentAddress += descriptor.size();
		}else{
			System.out.println("Fehler, zweimal die gleiche Variable deklariert");
		}
	}
	
//	public void undeclare(String ident){
//		descriptorMap.remove(ident);
//		addressMap.remove(ident);
//		
//	}


	public Descriptor descriptorFor(String ident) {
		Descriptor d = descriptorMap.get(ident);
		if(d == null && parentTable != null){
			return parentTable.descriptorFor(ident);
		}
		return d;
	}

	

	
	public int addressOf(String ident) {
		
		if(addressMap.containsKey(ident)){
			return addressMap.get(ident);
		}
		if(parentTable != null){
			return parentTable.addressOf(ident);
		}
		return -1;
		
		
	}

	
	public int size() {
		return currentAddress;
	}
	
	public String toString(){
		StringBuffer result = new StringBuffer();
		for(Map.Entry<String, Integer> e : addressMap.entrySet()){
			result.append(e.getKey());
			result.append(" : ");
			result.append(e.getValue());
			result.append("\n");
		}
		result.append("\n");
		
		for(Map.Entry<String, Descriptor> e : descriptorMap.entrySet()){
			result.append(e.getKey());
			result.append(" : ");
			result.append(e.getValue());
			result.append("\n");
		}
		result.append("\n");
		
		for(Map.Entry<String, Integer> e : constMap.entrySet()){
			result.append(e.getKey());
			result.append(" : ");
			result.append(e.getValue());
			result.append("\n");
		}
		result.append("\n");
		
		result.append("currentAddress: " + currentAddress + "\n");
		result.append("-----------------------------------------\n\n");
		result.append("parentTable: \n" + parentTable + "\n");
		
		return result.toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((addressMap == null) ? 0 : addressMap.hashCode());
		result = prime * result
				+ ((constMap == null) ? 0 : constMap.hashCode());
		result = prime * result + currentAddress;
		result = prime * result
				+ ((descriptorMap == null) ? 0 : descriptorMap.hashCode());
		result = prime * result
				+ ((parentTable == null) ? 0 : parentTable.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SymbolTable other = (SymbolTable) obj;
		if (addressMap == null) {
			if (other.addressMap != null)
				return false;
		} else if (!addressMap.equals(other.addressMap))
			return false;
		if (constMap == null) {
			if (other.constMap != null)
				return false;
		} else if (!constMap.equals(other.constMap))
			return false;
		if (currentAddress != other.currentAddress)
			return false;
		if (descriptorMap == null) {
			if (other.descriptorMap != null)
				return false;
		} else if (!descriptorMap.equals(other.descriptorMap))
			return false;
		if (parentTable == null) {
			if (other.parentTable != null)
				return false;
		} else if (!parentTable.equals(other.parentTable))
			return false;
		return true;
	}
}
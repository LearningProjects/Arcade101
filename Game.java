import java.io.*;
import java.io.IOException;
import java.util.ArrayList;

public class Game implements FileObject {
	protected int id;
	protected String name;
	protected int categoryID;
	protected String descr;
	protected String company;
	protected float price;
	protected float size;
	protected short year;

    public Game(String name, int categoryID, String descr, String company, float price, float size, short year) {
        this.id = -1;
        this.name = name;
        this.categoryID = categoryID;
        this.descr = descr;
        this.company = company;
        this.price = price;
        this.size = size;
        this.year = year;
    }

    public Game() {
        this.id = -1;
        this.name = "";
        this.categoryID = -1;
        this.descr = "";
        this.company = "";
        this.price = 0.0f;
        this.size = 0.0f;
        this.year = 0;
    }    

    public int getId() {
    	return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(data);
        out.writeInt(this.id);
        out.writeUTF(this.name);
        out.writeInt(this.categoryID);
        out.writeUTF(this.descr);
        out.writeUTF(this.company);
        out.writeFloat(this.price);
        out.writeFloat(this.size);
        out.writeShort(this.year);
        return data.toByteArray();
    }
    
    public void fromByteArray(byte[] b) throws IOException {
        ByteArrayInputStream data = new ByteArrayInputStream(b);
        DataInputStream in = new DataInputStream(data);
        this.id = in.readInt();
        this.name = in.readUTF();
        this.categoryID = in.readInt();
        this.descr = in.readUTF();
        this.company = in.readUTF();
        this.price = in.readFloat();
        this.size = in.readFloat();
        this.year = in.readShort();
    }
    public String toString(String categoryName){
        return "Id: " + this.id +
               "\nNome: " + this.name +
               "\nCategoria: " + categoryName +
               "\nDescrição: " + this.descr +
               "\nEmpresa: " + this.company +
               "\nPreço: R$" + this.price +
               "\nTamanho em disco: " + this.size + "(GBs)"+
               "\nAno de Lançamento: " + this.year + "\n";
    }
}
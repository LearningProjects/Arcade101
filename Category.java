import java.io.*;
import java.io.IOException;
import java.util.ArrayList;

public class Category implements FileObject {
	protected int id;
	protected String name;

    public Category(String name) {
        this.id = -1;
        this.name = name;
    }

    public Category() {
        this.id = -1;
        this.name = "";
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
        return data.toByteArray();
    }
    
    public void fromByteArray(byte[] b) throws IOException {
        ByteArrayInputStream data = new ByteArrayInputStream(b);
        DataInputStream in = new DataInputStream(data);
        this.id = in.readInt();
        this.name = in.readUTF();
    }
    
    public String toString(){
        return "Id: " + this.id +
               "\nNome: " + this.name + "\n";
    }
}
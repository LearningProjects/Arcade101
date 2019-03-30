import java.io.*;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

public class Archive<T extends FileObject> {
    private RandomAccessFile file;
    private Index index;
    private String fileName;
    private String indexName;
    private Constructor<T> constructor;


    public Archive(Constructor<T> c, String fileName, String indexName) throws IOException {
        this.fileName = fileName;
        this.indexName = indexName;
        this.constructor = c;
        this.file = new RandomAccessFile(this.fileName, "rw");

        this.index = new Index(20, this.indexName);

        if (file.length() < 4) {
            file.writeInt(0);
            index.apagar();
        }
    }
    
    public int include(T obj, int id) throws IOException {
        int lastID;
        file.seek(0);
        lastID = file.readInt();
        file.seek(0);
        if(id == -1){
            file.writeInt(lastID+1);
            obj.setId(lastID+1);
        }
        else{
            obj.setId(id);
        }        
        file.seek(file.length());
        long address = file.getFilePointer();
        byte[] b;
        b = obj.toByteArray();
        file.writeByte(' ');
        file.writeShort(b.length);
        file.write(b);
        index.inserir(obj.getId(), address);
        return obj.getId();
    }
    
    public T read(int id) throws Exception {
        T obj = constructor.newInstance();
        short size;
        byte[] b;
        byte dead;

        long address = index.buscar(id);
        if(address != -1) {
            file.seek(address);
            dead = file.readByte();
            size = file.readShort();
            b = new byte[size];
            file.read(b);
            obj.fromByteArray(b);
            return obj;
        } 
        return null;
    }
    
    public boolean exclude(int id) throws Exception {
        T obj = constructor.newInstance();
        short size;
        byte[] b;
        byte dead;
        long address;

        address = index.buscar(id);
        if(address != -1) {
            file.seek(address);
            file.writeByte('*');
            index.excluir(id);
            return true;
        }
        return false;
    }
    
    public ArrayList<T> list() throws Exception {
        ArrayList<T> list = new ArrayList<>();
        T obj;
        short size;
        byte[] b;
        byte dead;

        file.seek(4);
        while(file.getFilePointer() < file.length()) {
            dead = file.readByte();
            size = file.readShort();
            b = new byte[size];
            file.read(b);
            obj = constructor.newInstance();
            obj.fromByteArray(b);
            if(dead==' ')
                list.add(obj);
        }
        return list;
    }
    
}

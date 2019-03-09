import java.io.IOException;

public interface FileObject {
    public int getId();
    public void setId(int id);
    public byte[] toByteArray() throws IOException;
    public void fromByteArray(byte[] b) throws IOException;   
}

import java.io.File;

class SystemDrives
{
 public static void main(String args[])
 {
  File arr[] = File.listRoots();
  for(File f : arr)
  {
    System.out.println("Drive : "  + f.getAbsolutePath());
    System.out.println("Size : "  + f.getTotalSpace()/(1024*1024*1024.0));
    System.out.println("Free Space : "  + f.getFreeSpace()/(1024*1024*1024.0));
    System.out.println("Usable Space : "  + f.getUsableSpace()/(1024*1024*1024.0));
    System.out.println(f.exists());
    System.out.println();

  }
 }//main
}//SystemDrives
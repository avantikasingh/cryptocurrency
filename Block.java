
import java.security.MessageDigest;
import java.sql.Timestamp;
public class Block
{
  private int index;
  private String timestamp; //number of milliseconds since 1/1/1970.
  String hash;
  String previousHash;
  private int nonce;

  //Block constructor
  public Block(int index,String previousHash)
  { 
    this.index=index;
    this.previousHash=previousHash;
    Timestamp ts = new Timestamp(System.currentTimeMillis());
    this.timestamp=ts.toString();
    this.hash=calculateHash(); 
    this.nonce=0;
  }
  public String calculateHash()
  {
    String s=Integer.toString(this.index)+this.previousHash+this.timestamp+this.nonce;
    String calculatedHash=calculateSHA256(s);
    return calculatedHash;
  /*  SHA256InJava sj = new SHA256InJava();
    String data= Integer.toString(this.index)+this.previousHash+this.timestamp;
    String hash = sj.getSHA256Hash(data);
    return hash;*/
  } 

  //Applies SHA256 hash to an input string s and returns the result
  public String calculateSHA256(String s)
  {
   try
   { 
      //Java provides inbuilt MessageDigest class for SHA-256 hashing:
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] encodedhash = digest.digest(s.getBytes());

      //use a custom byte to hex converter to get the hashed value in hexadecimal:

        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < encodedhash.length; i++)
        {
          String hex = Integer.toHexString(0xff & encodedhash[i]);
          if(hex.length() == 1) hexString.append('0');
          hexString.append(hex);
        } 
      return hexString.toString(); 
   }
      
  catch(Exception e)
    { 
      throw new RuntimeException(e);
    } 
  }

  public void mineBlock(int difficulty)
  { 
    char x[]=new char[difficulty];
    String result=new String(x);     //Creates a string of n=difficulty no. of zeroes
    result=result.replace("\0","0"); 
    while(!(hash.substring(0,difficulty).equals(result)))     //checks if current hash has (n=difficulty) no of zeroes in the beginning
    { 
     this.nonce++; 
      hash=calculateHash();
    } 
    System.out.println("Block Mined!!");
    System.out.println("Hash: "+hash); 
  }




}

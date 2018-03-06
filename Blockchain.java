import java.util.ArrayList;
//import com.google.gson.Gson;



public class Blockchain
{
  public static int difficulty = 5;
  public static ArrayList<Block> blockchain = new ArrayList<Block>();

  public static void main(String args[])
  {
    System.out.println("Trying to Mine block 1... ");
		blockchain.add(new Block(0, "0")); 
    blockchain.get(0).mineBlock(difficulty);
   
		System.out.println("Trying to Mine block 2... ");
		blockchain.add(new Block(1,blockchain.get(blockchain.size()-1).hash));
    blockchain.get(1).mineBlock(difficulty);

		System.out.println("Trying to Mine block 3... ");
		blockchain.add(new Block(2,blockchain.get(blockchain.size()-1).hash));
    blockchain.get(2).mineBlock(difficulty);

		System.out.println("\nBlockchain is Valid: " + isChainValid());

	/*	String blockchainJson = StringUtil.getJson(blockchain);
		System.out.println("\nThe block chain: ");
		System.out.println(blockchainJson); */
  }


  //Blockchain constructor
/*  public Blockchain()
  {
     this.blockchain.add(genesisBlock());
  }
  //Genesis Block is Hardcoded with previous hash="0"
  public static Block genesisBlock()
  {
    return new Block(1,"0");
  } */

  public static Boolean isChainValid()
  {
    for(int i=1;i<blockchain.size();i++)
    {
      Block currentBlock=blockchain.get(i);
      Block previousBlock=blockchain.get(i-1);
      if(!(currentBlock.hash.equals(currentBlock.calculateHash())))     //check if hash of individual blocks is valid
      {
        System.out.println("Hash of Blocks Invalid!!");
        return false;
      }
      if(!(currentBlock.previousHash.equals(previousBlock.calculateHash())))  //check if previousHash of each block is equal to hash of its previous blocks
      { 
        System.out.println("Previous Hash of Blocks Invalid!!");
        return false;
      }
      String hash=currentBlock.calculateHash();
      String result=new String(new char[difficulty]).replace('\0','0');
      if(!(hash.substring(0,difficulty).equals(result)))    //checks if Proof of Work part has been done
      {
        System.out.println("Block not mined");
        return false;
      }

    }
    System.out.println("Chain Valid!!");
    return true;
  }

/*  public static Block getLatestBlock()
  {
    return blockchain.get(blockchain.size()-1);
  } */

  public static void addBlock(Block newBlock)
  {
	  blockchain.add(newBlock);
    newBlock.mineBlock(difficulty);  //Proof of Work
    
  }

}

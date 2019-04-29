package dev.jdsgames.orecore.events;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFormEvent;

import dev.jdsgames.orecore.OreCore;

public class CobbleCreated implements Listener 
{
	// These variables are the chances of having the ore be something cooler. (Will use Config Later)
	private final double COBBLESTONE_CHANCE  = 0.9300;		// 93.00%
	private final double COAL_ORE_CHANCE     = 0.0300; 		//  3.00%
	private final double IRON_ORE_CHANCE     = 0.0150;  	//  1.50% 
	private final double GOLD_ORE_CHANCE     = 0.0050;  	//  0.50%
	private final double LAPIS_ORE_CHANCE    = 0.0075; 		//  0.75%
	private final double REDSTONE_ORE_CHANCE = 0.0075;  	//  0.75%
	private final double DIAMOND_ORE_CHANCE  = 0.0030;		//  0.30%
	private final double EMERALD_ORE_CHANCE  = 0.0020;		//  0.20%

	
	// List of Created Ore Blocks
	private ArrayList<Block> createdOreBlocks;
	
	// Obtain Block Information
	int totalBlocks = 0, numCobble  = 0, numCoal    = 0, 
		numIron     = 0, numGold    = 0, numLapis   = 0, 
		numRedstone = 0, numEmerald = 0, numDiamond = 0;
	int cycle = 0;
	
	// Constructor
	public CobbleCreated(OreCore plugin) 
	{
		// For every new CobbleCreated event create a new list of created ore blocks.
		this.createdOreBlocks = new ArrayList<Block>();
	}
	
	@EventHandler
	public void cobbleCreation(BlockFormEvent bfe) 
	{
		// Obtain the created block
		Block block = bfe.getBlock();
		Material blockType = block.getType();
		
		// Determine if CobbleStone has been generated.
		if(blockType.compareTo(Material.LAVA) == 0)
		{
			// Determine what the formed block type should be
			blockType = calculateBlockType();
			
			// Determine if the block is CobbleStone or not.
			if(blockType.compareTo(Material.COBBLESTONE) != 0) 
			{
				// Change the Event's Block
				bfe.getNewState().setType(blockType);
				
				// Change Block
				block.setType(blockType);
				
				// If the block is not CobbleStone add it to our Ore Storage
				createdOreBlocks.add(block);
			}
			else 
			{
				numCobble++;
			}
			
			totalBlocks++;
			
			// Debug Statistics
			if(totalBlocks >= 100000) 
			{
				cycle++;
				Bukkit.getServer().broadcastMessage("OreCore Statistics -----------------------");
				Bukkit.getServer().broadcastMessage("Total Blocks: " + (totalBlocks + (100000*(cycle-1))));
				Bukkit.getServer().broadcastMessage("Block Cycle:  " + cycle                    );
				Bukkit.getServer().broadcastMessage("Cobblestone:  " + numCobble                );
				Bukkit.getServer().broadcastMessage("Coal Ore:     " + numCoal                  );
				Bukkit.getServer().broadcastMessage("Iron Ore:     " + numIron                  );
				Bukkit.getServer().broadcastMessage("Gold Ore:     " + numGold                  );
				Bukkit.getServer().broadcastMessage("Lapis Ore:    " + numLapis                 );
				Bukkit.getServer().broadcastMessage("Redstone Ore: " + numRedstone              );
				Bukkit.getServer().broadcastMessage("Diamond Ore:  " + numDiamond               );
				Bukkit.getServer().broadcastMessage("Emerald Ore:  " + numEmerald               );
				Bukkit.getServer().broadcastMessage("------------------------------------------");
				
				// Reset the Base of 100000
				totalBlocks -= 100000;
			}
		}
	}

	// Calculate what the block should be when formed.
	private Material calculateBlockType() 
	{
		Material blockMaterial;
		
		// Determine if the Block is Destined to be CobbleStone
		if(randomChance() <= COBBLESTONE_CHANCE) 
		{
			// Block is Plain Cobblestone
			blockMaterial = Material.COBBLESTONE;
		}
		else 
		{
			// Determine Block Material Type
			if(randomChance() <= COAL_ORE_CHANCE) 
			{
				// Block is Coal Ore
				blockMaterial = Material.COAL_ORE;
				
				numCoal++;
			}
			else if(randomChance() <= IRON_ORE_CHANCE) 
			{
				// Block is Iron Ore
				blockMaterial = Material.IRON_ORE;
				
				numIron++;
			}
			else if(randomChance() <= GOLD_ORE_CHANCE)
			{
				// Block is Gold Ore
				blockMaterial = Material.GOLD_ORE;
				
				numGold++;
			}
			else if(randomChance() <= LAPIS_ORE_CHANCE) 
			{
				// Block is Lapis Ore
				blockMaterial = Material.LAPIS_ORE;
				
				numLapis++;
			}
			else if(randomChance() <= REDSTONE_ORE_CHANCE) 
			{
				// Block is Redstone Ore
				blockMaterial = Material.REDSTONE_ORE;
				
				numRedstone++;
			}
			else if (randomChance() <= DIAMOND_ORE_CHANCE) 
			{
				// Block is Diamond Ore
				blockMaterial = Material.DIAMOND_ORE;
				
				numDiamond++;
			}
			else if(randomChance() <= EMERALD_ORE_CHANCE) 
			{
				// Block is Emerald Ore
				blockMaterial = Material.EMERALD_ORE;
				
				numEmerald++;
			}
			else 
			{
				// If the block was not special we enslave its nature to be CobbleStone
				blockMaterial = Material.COBBLESTONE;
			}
		}
		
		// Return the Material of the Block
		return blockMaterial;
	}
	
	// Generate the Random Chance Factor
	private double randomChance() 
	{
		Random gen = new Random();
		return gen.nextDouble();
	} 
	
	// Get the Created Ores List
	public ArrayList<Block> getCreatedOreBlocks()
	{
		return this.createdOreBlocks;
	}
	
	// Set the Created Ores List
	public void setCreatedOreBlocks(ArrayList<Block> createdOreBlocks) 
	{
		this.createdOreBlocks = createdOreBlocks;
	}
}
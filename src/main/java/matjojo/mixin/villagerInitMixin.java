package matjojo.mixin;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.village.VillagerType;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VillagerEntity.class)
public abstract class villagerInitMixin extends MerchantEntity {
	
	public static final Tag<Item> EMERALD_ITEMS = TagFactory.ITEM.create(new Identifier("villagersfollowemeralds", "emerald_items"));

	public villagerInitMixin(EntityType<? extends MerchantEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "<init>(Lnet/minecraft/entity/EntityType;Lnet/minecraft/world/World;Lnet/minecraft/village/VillagerType;)V",
			at = @At(value = "TAIL"))
	private void inject(EntityType<? extends VillagerEntity> entityType, World world, VillagerType type, CallbackInfo ci) {
		this.goalSelector.add(2, new TemptGoal(this, .4D, Ingredient.ofItems(EMERALD_ITEMS), false));
	}

}

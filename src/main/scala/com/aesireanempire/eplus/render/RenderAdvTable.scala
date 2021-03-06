package com.aesireanempire.eplus.render

import com.aesireanempire.eplus.blocks.entities.TileEntityAdvEnchantmentTable
import cpw.mods.fml.client.FMLClientHandler
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.AdvancedModelLoader
import net.minecraftforge.common.util.ForgeDirection
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11._

object RenderAdvTable extends TileEntitySpecialRenderer {
    val model = AdvancedModelLoader.loadModel(new ResourceLocation("eplus", "models/table.tcn"))
    val texture = new ResourceLocation("eplus", "textures/blocks/table.png")

    override def renderTileEntityAt(te: TileEntity, x: Double, y: Double, z: Double, time: Float) {

        glPushMatrix()
        glTranslated(x + 0.5, y + 0.5, z + 0.5)

        glShadeModel(GL_SMOOTH)
        glEnable(GL_BLEND)
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA)

        te.asInstanceOf[TileEntityAdvEnchantmentTable].direction match {
            case ForgeDirection.UNKNOWN =>
            case ForgeDirection.NORTH  | ForgeDirection.UP => glRotated(0, 0, 1, 0)
            case ForgeDirection.WEST | ForgeDirection.DOWN => glRotated(90, 0, 1, 0)
            case ForgeDirection.SOUTH => glRotated(180, 0, 1, 0)
            case ForgeDirection.EAST => glRotated(270, 0, 1, 0)
        }


        FMLClientHandler.instance.getClient.renderEngine.bindTexture(texture)
        model.renderAll()

        glShadeModel(GL_FLAT)
        glDisable(GL_LINE_SMOOTH)
        glDisable(GL_POLYGON_SMOOTH)
        glDisable(GL_BLEND)

        GL11.glPopMatrix()
    }

    def renderItem() {
        glPushMatrix()
        glRotated(270, 0, 1, 0)

        glShadeModel(GL_SMOOTH)
        glEnable(GL_BLEND)
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA)

        FMLClientHandler.instance.getClient.renderEngine.bindTexture(texture)
        model.renderAll()

        glShadeModel(GL_FLAT)
        glDisable(GL_LINE_SMOOTH)
        glDisable(GL_POLYGON_SMOOTH)
        glDisable(GL_BLEND)

        GL11.glPopMatrix()
    }

}

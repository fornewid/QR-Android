package soup.qr

import android.content.Intent
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService

class BarcodeTile : TileService() {

    override fun onTileAdded() {
        super.onTileAdded()
        updateTileUi()
    }

    override fun onStartListening() {
        super.onStartListening()
        updateTileUi()
    }

    private fun updateTileUi() {
        qsTile.state = Tile.STATE_INACTIVE
        qsTile.updateTile()
    }

    override fun onClick() {
        val executeAction = {
            val intent = Intent(this, BarcodeActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivityAndCollapse(intent)
        }
        if (isLocked || isSecure) {
            unlockAndRun(executeAction)
        } else {
            executeAction()
        }
    }
}
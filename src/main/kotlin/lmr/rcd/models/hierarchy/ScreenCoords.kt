package lmr.rcd.models.hierarchy

data class ScreenCoords(
    var zoneIdx: Byte = -1,
    var sceneIdx: Byte = -1,
    var screenIdx: Byte = -1
) {
    fun toTerseString() = "$zoneIdx, $sceneIdx, $screenIdx"
}
import Sol15.Block
import Sol15.Dir
import Sol15.Pos

val grid: MutableMap<Pos, Block> = mutableMapOf()
val droidPos = Pos(0, 0)
grid.put(droidPos, Block.Empty)
grid.put(droidPos + Dir.E.offset(), Block.Oxygen)
grid
droidPos.near() - grid.keys
Dir.N
val newToVisit = Dir.near().map { it to droidPos + it.offset() }
    .filterNot { p -> grid.containsKey(p.second) }
newToVisit
val ls = Dir.near().map { it to droidPos + it.offset() }
grid.keys.contains(ls[0].second)
grid.keys.contains(ls[1].second)
grid.keys.contains(ls[2].second)
grid.keys.contains(ls[3].second)
ls.filterNot { p -> grid.containsKey(p.second) }

Dir.near().map { it to droidPos + it.offset() }.filterNot { p -> grid.containsKey(p.second) }
grid

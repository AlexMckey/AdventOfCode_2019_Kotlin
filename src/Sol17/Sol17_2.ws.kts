import Sol17.LRS

//val prog =
//    "L,12,R,8,L,10,L,10,R,6,R,4,R,12,R,12,R,12,L,8,L,10,L,10,R,6,R,4,R,12,L,10,R,8,R,4,L,10,R,6,R,4,R,12,R,12,R,8,L,10,L,10,L,10,R,8,R,4,L,10,R,6,R,4,R,12,L,10,R,8,R,4,L,10"
val prog = "R,8,R,8,R,4,R,4,R,8,L,6,L,2,R,4,R,4,R,8,R,8,R,8,L,6,L,2"
LRS.lrs(prog)
val progl1 = prog.split(",")
val a = LRS.lrs(progl1)
a
val astr = a.joinToString(",")
val proga = prog.replace(astr, "A")
val progl2 = proga.split(",")
val b = LRS.lrs(progl2)
b
val bstr = b.joinToString(",")
val progb = proga.replace(bstr, "B")
val progl3 = progb.split(",")
val c = LRS.lrs(progl3)
c
val cstr = c.joinToString(",")
val progc = progb.replace(cstr, "C")
progc
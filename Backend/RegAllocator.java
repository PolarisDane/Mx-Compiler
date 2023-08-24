package Backend;

import Assembly.ASMBlock;
import Assembly.ASMProgram;
import Assembly.Inst.*;
import Assembly.Operand.Imm;
import Assembly.Operand.PhyReg;
import Assembly.Operand.Reg;
import Assembly.Operand.VirtualReg;

import java.util.ArrayList;
import java.util.LinkedList;

public class RegAllocator {
    public ASMProgram program;
    int stackLength;
    int stackStart;
    PhyReg Regt0 = new PhyReg("t0");
    PhyReg Regt1 = new PhyReg("t1");
    PhyReg Regt2 = new PhyReg("t2");
    PhyReg Regsp = new PhyReg("sp");
    LinkedList<ASMBaseInst> insts;
    public RegAllocator(ASMProgram program) {
        this.program = program;
    }

    public void work() {
        for (var func: program.functions) {
            stackLength = func.stackLength;
            stackStart = func.stackStart;
            for (var block: func.blocks) {
                visitAndAllocate(block);
            }
        }
//        System.out.println(program.toString());
    }

    public void visitAndAllocate(ASMBlock block) {
        insts = new LinkedList<>();
        for (var inst: block.insts) {
            if (inst.rs1 != null && !(inst.rs1 instanceof PhyReg)) {
                allocate(Regt0, inst.rs1, true);
                inst.rs1 = Regt0;
            }
            if (inst.rs2 != null && !(inst.rs2 instanceof PhyReg)) {
                allocate(Regt1, inst.rs2, true);
                inst.rs2 = Regt1;
            }
            insts.add(inst);
            if (inst.rd != null && !(inst.rd instanceof PhyReg)) {
                allocate(Regt2, inst.rd, false);
                inst.rd = Regt2;
            }
        }
        block.insts = insts;
    }

    public void allocate(PhyReg phyReg, Reg reg, boolean isSrc) {
        int offset = ((VirtualReg) reg).id * 4 + stackStart;
        if (offset < (1 << 11)) {
            if (isSrc) {
                insts.add(new ASMLoadInst(32, phyReg, Regsp, new Imm(offset)));
            }
            else {
                insts.add(new ASMStoreInst(32, Regsp, phyReg, new Imm(offset)));
            }
        }
        else {
            if (isSrc) {
                insts.add(new ASMLiInst(Regt0, new Imm(offset)));
                insts.add(new ASMRTypeInst(Regt0, Regt0, Regsp, "add"));
                insts.add(new ASMLoadInst(32, phyReg, Regt0));
            }
            else {
                insts.add(new ASMLiInst(Regt0, new Imm(offset)));
                insts.add(new ASMRTypeInst(Regt0, Regt0, Regsp, "add"));
                insts.add(new ASMStoreInst(32, Regt0, phyReg));
            }
        }
    }

}

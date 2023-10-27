package xxl.core;

public abstract class BinaryFunction extends Function {
    private String _name;
    protected Content _arg0;
    protected Content _arg1;

    protected abstract Literal compute();

    public BinaryFunction(String name, Content arg0, Content arg1) {
        super(name);
        _name = name;
        _arg0 = arg0;
        _arg1 = arg1;
    }
    
    public String toString() {
        String[] ref0 = _arg0.toString().split("=");
        String[] ref1 = _arg1.toString().split("=");
        String s = "";
        if (ref0.length == 1) {
            s = "=" + _name + "(" + ref0[0] + ",";
        } else{
            s = "=" + _name + "(" + ref0[1] + ",";
        }
        if (ref1.length == 1){
            s += ref1[0] +")";
        }else{
            s += ref1[1] +")";
        }
        return s;
    }

    public Content getArg0() {
        return _arg0;
    }

    public Content getArg1() {
        return _arg1;
    }
}

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class compiler {
    private static ArrayList<String> timefuncs = new ArrayList<>();
    private static ArrayList<ArrayList<String>> funcs = new ArrayList<>();


    private static void handleFuncs(ParseTree parseTree) {
        if (parseTree.getChildCount() == 3 && parseTree.getChild(2).getChild(0).getClass().toString().equals("class grammerParser$DEFFUNCContext")) {
            ParseTree args = parseTree.getChild(2).getChild(0).getChild(1);
            ArrayList<String> arrayList = new ArrayList<>();
            String fName = parseTree.getChild(0).getText();
            arrayList.add(fName);
            String returnValue = parseTree.getChild(2).getChild(0).getChild(4).getText();
            arrayList.add(returnValue);
            String arg1 = args.getChild(0).getText();
            arrayList.add(arg1);
            for (int i = 1; i < args.getChildCount(); i++) {
                if (i % 2 == 0) {
                    String argN = args.getChild(i).getText();
                    arrayList.add(argN);
                }

            }
            funcs.add(arrayList);
        }


    }

    private static void handleTimeFuncs(ParseTree parseTree) {
        if (parseTree.getChildCount() == 3 && parseTree.getChild(2).getChild(0).getText().equals("TimeFunc (Date) -> Double")) {
            String fName = parseTree.getChild(0).getText();
            timefuncs.add(fName);
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("#include <iostream>");
        System.out.println("#include <cstdlib>");
        System.out.println();
        System.out.println("using namespace std;");
        System.out.println();
        System.out.println("int max1 = 0;");
        System.out.println();
        System.out.println("int one() {");
        System.out.println("\treturn 1;");
        System.out.println("}");
        System.out.println();
        String[] contracts = new String[0];
        File reader = new File("input.txt");
        PrintWriter writer = new PrintWriter("test.txt", "UTF-8");
        Scanner scanner = new Scanner(reader);
        Scanner scanner1 = new Scanner(System.in);
        boolean flag = true;
        int cnt = 0;
        int time = 0;
        int n;
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            if (s.replace("\n", "").equals("END")) {
                flag = false;
                String nextLine = scanner.nextLine();
                String[] splited = nextLine.split(" ");
                n = Integer.parseInt(splited[0]);
                time = Integer.parseInt(splited[1]);
                contracts = new String[n];
                writer.close();
            } else if (flag) {
                writer.println(s);
            } else {
                contracts[cnt] = s;
                cnt++;


            }
        }
        InputStream iinput = new FileInputStream(new File("test.txt"));
        CharStream input = CharStreams.fromStream(iinput);
        grammerLexer lexer = new grammerLexer(input);
        TokenStream tokens = new org.antlr.v4.runtime.CommonTokenStream(lexer);
        grammerParser parser = new grammerParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.pROGRAM();
        for (int i = 0; i < tree.getChildCount(); i++) {
            ParseTree tmp = tree.getChild(i);
            if (tree.getChild(i).getClass().toString().equals("class grammerParser$ASSIGNContext")) {
                if ( ! (i < tree.getChildCount()-1) || !tree.getChild(i + 1).getClass().toString().equals("class grammerParser$ASSIGNContext")
                            || !tree.getChild(i + 1).getChild(0).toString().equals(tree.getChild(i).getChild(0).toString()))
                        assign(tmp);
            }
            if (tree.getChild(i).getClass().toString().equals("class grammerParser$DEFContext")) {
                handleFuncs(tmp);
                handleTimeFuncs(tmp);
            }
        }
        System.out.println("int main() {");
        System.out.println("\tint sum = 0;\n" +
                "\tmax1 = " + (time + 1) + ";");
        for (String contract : contracts) {
            System.out.println("\tif(" + contract + "Value(" + time + ")>0){");
            System.out.println("\t\tsum += " + contract + "Value(" + time + ");");
            System.out.println("\t}");
        }
        System.out.println("\tcout<<sum;\n" +
                "}");
    }

    private static void assign(ParseTree p) {
        for (ArrayList<String> func : funcs) {
            if (func.get(0).equals(p.getChild(0).getText())) {
                System.out.print(func.get(1).toLowerCase() + " " + p.getChild(0).getText() + "Value(");
                for (int j = 2; j < func.size() - 1; j++) {
                    System.out.print(func.get(j).toLowerCase() + " arg" + (j - 1) + ", ");
                }
                System.out.println(func.get(func.size() - 1).toLowerCase() + " arg" + (func.size() - 2) + "){");
                System.out.println("\treturn " + p.getChild(2).getText() + ";");
                System.out.println("}");
                return;
            }
        }
        if (timefuncs.contains(p.getChild(0).getText())) {
            System.out.println("double " + p.getChild(0).getText() + "Value(int arg1){");
            System.out.println("\t return " + p.getChild(2).getText() + ";");
            System.out.println("}");
        } else {
            System.out.println("double " + p.getChild(0).getText() + "Value(int time){");

            if (p.getChild(2).getChild(0).getChildCount() > 0) {
                if (p.getChild(2).getChild(0).getChild(0).getText().equals("one()")) {
                    System.out.println("\treturn 1;");
                } else if (p.getChild(2).getChild(0).getChild(0).getText().equals("give")) {
                    if (p.getChild(2).getChild(0).getChild(2).getClass().toString().equals("class grammerParser$ARGSContext")) {
                        System.out.println("\treturn -1*" + p.getChild(2).getChild(0).getChild(2).getChild(0).getText() + "Value(time);");

                    } else {
                        System.out.println("\treturn -1*" + p.getChild(2).getChild(0).getChild(2).getText() + "Value(time);");
                    }
                } else if (p.getChild(2).getChild(0).getChild(0).getText().equals("scale")) {
                    if (p.getChild(2).getChild(0).getChild(2).getClass().toString().equals("class grammerParser$ARGSContext")) {
                        System.out.println("\treturn " + p.getChild(2).getChild(0).getChild(2).getChild(0).getText() + " * " +
                                p.getChild(2).getChild(0).getChild(2).getChild(2).getText() + "Value(time);");

                    } else {
                        System.out.println("\treturn " + p.getChild(2).getChild(0).getChild(2).getText() + " * " +
                                p.getChild(2).getChild(0).getChild(4).getText() + "Value(time);");
                    }
                } else if (p.getChild(2).getChild(0).getChild(0).getText().equals("truncate")) {
                    if (p.getChild(2).getChild(0).getChild(2).getClass().toString().equals("class grammerParser$ARGSContext")) {
                        System.out.println("\tif(time<" + p.getChild(2).getChild(0).getChild(2).getChild(0).getText() + ")");
                        System.out.println("\t\treturn " + p.getChild(2).getChild(0).getChild(2).getChild(2).getText() + "Value(time);");
                        System.out.println("\telse return 0;");
                    } else {
                        System.out.println("\tif(time<" + p.getChild(2).getChild(0).getChild(2).getText() + ")");
                        System.out.println("\t\treturn " + p.getChild(2).getChild(0).getChild(4).getText() + "Value(time);");
                        System.out.println("\telse return 0;");
                    }
                } else if (p.getChild(2).getChild(0).getChild(0).getText().equals("scaleX")) {
                    if (p.getChild(2).getChild(0).getChild(2).getClass().toString().equals("class grammerParser$ARGSContext")) {
                        System.out.println("\treturn " + p.getChild(2).getChild(0).getChild(2).getChild(0).getText() + "Value(time) * " +
                                p.getChild(2).getChild(0).getChild(2).getChild(2).getText() + "Value(time);");

                    } else {
                        System.out.println("\treturn " + p.getChild(2).getChild(0).getChild(2).getText() + "Value(time) * " +
                                p.getChild(2).getChild(0).getChild(4).getText() + "Value(time);");
                    }
                } else if (p.getChild(2).getChild(0).getChild(0).getText().equals("and")) {
                    if (p.getChild(2).getChild(0).getChild(2).getClass().toString().equals("class grammerParser$ARGSContext")) {
                        System.out.println("\treturn " + p.getChild(2).getChild(0).getChild(2).getChild(0).getText() + "Value(time) + " +
                                p.getChild(2).getChild(0).getChild(2).getChild(2).getText() + "Value(time);");

                    } else {
                        System.out.println("\treturn " + p.getChild(2).getChild(0).getChild(2).getText() + "Value(time) + " +
                                p.getChild(2).getChild(0).getChild(4).getText() + "Value(time);");
                    }
                } else if (p.getChild(2).getChild(0).getChild(0).getText().equals("or")) {
                    if (p.getChild(2).getChild(0).getChild(2).getClass().toString().equals("class grammerParser$ARGSContext")) {
                        System.out.println("\tif(time>=" + p.getChild(2).getChild(0).getChild(2).getChild(0).getText() + "Deadline())");
                        System.out.println("\t\treturn " + p.getChild(2).getChild(0).getChild(2).getChild(2).getText() + "Value(time);");
                        System.out.println("\telse if(time>=" + p.getChild(2).getChild(0).getChild(2).getChild(2).getText() + "Deadline())");
                        System.out.println("\t\treturn " + p.getChild(2).getChild(0).getChild(2).getChild(0).getText() + "Value(time);");
                        System.out.println("\telse if(" + p.getChild(2).getChild(0).getChild(2).getChild(2).getText() + "Value(time)>" +
                                p.getChild(2).getChild(0).getChild(2).getChild(0).getText() + "Value(time))");
                        System.out.println("\t\treturn " + p.getChild(2).getChild(0).getChild(2).getChild(2).getText() + "Value(time);");
                        System.out.println("\telse");
                        System.out.println("\t\treturn " + p.getChild(2).getChild(0).getChild(2).getChild(0).getText() + "Value(time)");
                    } else {
                        System.out.println("\tif(time>=" + p.getChild(2).getChild(0).getChild(2).getText() + "Deadline())");
                        System.out.println("\t\treturn " + p.getChild(2).getChild(0).getChild(4).getText() + "Value(time);");
                        System.out.println("\telse if(time>=" + p.getChild(2).getChild(0).getChild(4).getText() + "Deadline())");
                        System.out.println("\t\treturn " + p.getChild(2).getChild(0).getChild(2).getText() + "Value(time);");
                        System.out.println("\telse if(" + p.getChild(2).getChild(0).getChild(4).getText() + "Value(time)>" +
                                p.getChild(2).getChild(0).getChild(2).getText() + "Value(time))");
                        System.out.println("\t\treturn " + p.getChild(2).getChild(0).getChild(4).getText() + "Value(time);");
                        System.out.println("\telse");
                        System.out.println("\t\treturn " + p.getChild(2).getChild(0).getChild(2).getText() + "Value(time)");
                    }
                } else if (p.getChild(2).getChild(0).getChild(0).getText().equals("then")) {
                    if (p.getChild(2).getChild(0).getChild(2).getClass().toString().equals("class grammerParser$ARGSContext")) {
                        System.out.println("\tif(time<" + p.getChild(2).getChild(0).getChild(2).getChild(0).getText() + "Deadline())");
                        System.out.println("\t\treturn " + p.getChild(2).getChild(0).getChild(2).getChild(0).getText() + "Value(time);");
                        System.out.println("\telse");
                        System.out.println("\t\treturn " + p.getChild(2).getChild(0).getChild(2).getChild(2).getText() + "Value(time)");
                    } else {
                        System.out.println("\tif(time<" + p.getChild(2).getChild(0).getChild(2).getText() + "Deadline())");
                        System.out.println("\t\treturn " + p.getChild(2).getChild(0).getChild(2).getText() + "Value(time);");
                        System.out.println("\telse");
                        System.out.println("\t\treturn " + p.getChild(2).getChild(0).getChild(4).getText() + "Value(time)");
                    }
                }
            }
            System.out.println("}");

            System.out.println("double " + p.getChild(0).getText() + "Deadline(){");

            if (p.getChild(2).getChild(0).getChildCount() > 0) {
                if (p.getChild(2).getChild(0).getChild(0).getText().equals("one()")) {
                    System.out.println("\treturn max1;");
                } else if (p.getChild(2).getChild(0).getChild(0).getText().equals("give")) {
                    if (p.getChild(2).getChild(0).getChild(2).getClass().toString().equals("class grammerParser$ARGSContext")) {
                        System.out.println("\treturn " + p.getChild(2).getChild(0).getChild(2).getChild(0).getText() + "Deadline();");

                    } else {
                        System.out.println("\treturn " + p.getChild(2).getChild(0).getChild(2).getText() + "Deadline();");
                    }
                } else if (p.getChild(2).getChild(0).getChild(0).getText().equals("scale")) {
                    if (p.getChild(2).getChild(0).getChild(2).getClass().toString().equals("class grammerParser$ARGSContext")) {
                        System.out.println("\treturn " + p.getChild(2).getChild(0).getChild(2).getChild(2).getText() + "Deadline();");

                    } else {
                        System.out.println("\treturn " + p.getChild(2).getChild(0).getChild(4).getText() + "Deadline();");
                    }
                } else if (p.getChild(2).getChild(0).getChild(0).getText().equals("truncate")) {
                    if (p.getChild(2).getChild(0).getChild(2).getClass().toString().equals("class grammerParser$ARGSContext")) {
                        System.out.println("\treturn " + p.getChild(2).getChild(0).getChild(2).getChild(0).getText() + ";");

                    } else {
                        System.out.println("\treturn " + p.getChild(2).getChild(0).getChild(2).getText() + ";");
                    }
                } else if (p.getChild(2).getChild(0).getChild(0).getText().equals("scaleX")) {
                    if (p.getChild(2).getChild(0).getChild(2).getClass().toString().equals("class grammerParser$ARGSContext")) {
                        System.out.println("\treturn " + p.getChild(2).getChild(0).getChild(2).getChild(2).getText() + "Deadline();");

                    } else {
                        System.out.println("\treturn " + p.getChild(2).getChild(0).getChild(4).getText() + "Deadline();");
                    }
                } else if (p.getChild(2).getChild(0).getChild(0).getText().equals("and")
                        || p.getChild(2).getChild(0).getChild(0).getText().equals("or")
                        || p.getChild(2).getChild(0).getChild(0).getText().equals("then")) {
                    if (p.getChild(2).getChild(0).getChild(2).getClass().toString().equals("class grammerParser$ARGSContext")) {
                        System.out.println("\treturn max(" + p.getChild(2).getChild(0).getChild(2).getChild(0).getText() + "Deadline() , " +
                                p.getChild(2).getChild(0).getChild(2).getChild(2).getText() + "Deadline());");

                    } else {
                        System.out.println("\treturn max(" + p.getChild(2).getChild(0).getChild(2).getText() + "Deadline() , " +
                                p.getChild(2).getChild(0).getChild(4).getText() + "Deadline();)");
                    }
                }
            }
            System.out.println("}");
        }
    }
}

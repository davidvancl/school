// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GramatikaParser}.
 */
public interface GramatikaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GramatikaParser#r}.
	 * @param ctx the parse tree
	 */
	void enterR(GramatikaParser.RContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramatikaParser#r}.
	 * @param ctx the parse tree
	 */
	void exitR(GramatikaParser.RContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramatikaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(GramatikaParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramatikaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(GramatikaParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramatikaParser#operand}.
	 * @param ctx the parse tree
	 */
	void enterOperand(GramatikaParser.OperandContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramatikaParser#operand}.
	 * @param ctx the parse tree
	 */
	void exitOperand(GramatikaParser.OperandContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramatikaParser#convert}.
	 * @param ctx the parse tree
	 */
	void enterConvert(GramatikaParser.ConvertContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramatikaParser#convert}.
	 * @param ctx the parse tree
	 */
	void exitConvert(GramatikaParser.ConvertContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramatikaParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterOperation(GramatikaParser.OperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramatikaParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitOperation(GramatikaParser.OperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramatikaParser#data_type}.
	 * @param ctx the parse tree
	 */
	void enterData_type(GramatikaParser.Data_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramatikaParser#data_type}.
	 * @param ctx the parse tree
	 */
	void exitData_type(GramatikaParser.Data_typeContext ctx);
}
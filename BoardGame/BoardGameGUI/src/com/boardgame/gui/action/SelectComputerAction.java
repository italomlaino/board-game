package com.boardgame.gui.action;

import com.boardgame.db.Database;
import com.boardgame.model.Match;
import com.boardgame.model.ai.decisionrule.DecisionRule;
import com.boardgame.model.ai.decisionrule.DecisionRuleFactory;
import com.boardgame.model.ai.expertsystem.ExpertSystem;
import com.boardgame.model.ai.heuristic.Heuristic;
import com.boardgame.model.player.Computer;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;

public class SelectComputerAction extends SelectPlayerAction {

    private Computer last;

    public SelectComputerAction(Match match, byte playerID) {
        super(match, playerID, null);

        this.last = null;

        putValue(Action.NAME, "Computer");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Computer temp = editComputerOptions(last);

        player = temp;
        last = temp;

        super.actionPerformed(e);
    }

    private Computer editComputerOptions(Computer computer) {
        NumberFormatter formatter = new NumberFormatter(
                NumberFormat.getIntegerInstance());
        formatter.setMinimum(-1);

        BasicComboBoxRenderer render = new BasicComboBoxRenderer() {

            @Override
            public Component getListCellRendererComponent(JList list,
                                                          Object value, int index, boolean isSelected,
                                                          boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index,
                        isSelected, cellHasFocus);

                if (value != null) {

                    Class cl;
                    if (value instanceof Class) {
                        cl = (Class) value;
                    } else {
                        cl = (Class) value.getClass();
                    }

                    setText(cl.getSimpleName());
                }

                return this;
            }
        };

        JComboBox<Class> decisionRuleCB = new JComboBox<Class>(Database
                .getInstance().getDecisionRules().toArray(new Class[0]));
        decisionRuleCB.setRenderer(render);

        JFormattedTextField limitField = new JFormattedTextField(formatter);
        JComboBox<Heuristic> heuristicCB = new JComboBox<Heuristic>(Database
                .getInstance().getHeuristics().toArray(new Heuristic[0]));
        heuristicCB.setRenderer(render);

        JComboBox<ExpertSystem> expertSystemCB = new JComboBox<ExpertSystem>(Database
                .getInstance().getExpertSystems().toArray(new ExpertSystem[0]));
        expertSystemCB.setRenderer(render);

        if (computer != null) {
            decisionRuleCB.setSelectedItem(computer.getDecisionRule()
                    .getClass());
            limitField.setValue(computer.getDepthLimit());
            heuristicCB.setSelectedItem(computer.getHeuristic());
            expertSystemCB.setSelectedItem(computer.getExpertSystem());
        } else {
            limitField.setValue(5);

            int limit = (Integer) limitField.getValue();
            Heuristic heuristic = (Heuristic) heuristicCB.getSelectedItem();
            Class decisionRuleClass = (Class) decisionRuleCB.getSelectedItem();
            DecisionRule decisionRule = DecisionRuleFactory.getDefaultFactory()
                    .makeDecisionRule(decisionRuleClass);
            ExpertSystem expertSystem = (ExpertSystem) expertSystemCB
                    .getSelectedItem();

            last = new Computer(decisionRule, heuristic, expertSystem, limit);
        }

        Object[] message = {"Decision Rule", decisionRuleCB, "Depth Limit",
                limitField, "Heuristic", heuristicCB, "Expert System",
                expertSystemCB};

        int option = JOptionPane.showConfirmDialog(null, message,
                "Computer Selection Options", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            int limit = (Integer) limitField.getValue();
            Heuristic heuristic = (Heuristic) heuristicCB.getSelectedItem();
            Class decisionRuleClass = (Class) decisionRuleCB.getSelectedItem();
            DecisionRule decisionRule = DecisionRuleFactory.getDefaultFactory()
                    .makeDecisionRule(decisionRuleClass);
            ExpertSystem expertSystem = (ExpertSystem) expertSystemCB
                    .getSelectedItem();

            return new Computer(decisionRule, heuristic, expertSystem, limit);
        } else {
            return last;
        }
    }
}

package com.ivashchenko.practice3.task31;

/**
 * This class is used to describe cell phone with its components.
 * Component is an inner class.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class CellPhone {
    private String modelName;
    private Component[] components;
    private int indexToInsert = 0;

    public CellPhone(String modelName, int componentsNumber) {
        this.modelName = modelName;
        this.components = new Component[componentsNumber];
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void addComponent(String componentName, String componentDescription, String[] specifications) {
        Component component = new Component(componentName, componentDescription, specifications);
        if ((indexToInsert >= components.length) || (indexToInsert < 0)) {
            return;
        }
        components[indexToInsert++] = component;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Model name: " + modelName + "\n");
        stringBuilder.append("Components: \n");
        for (Component component: components) {
            stringBuilder.append(component.toString());
        }
        return stringBuilder.toString();
    }

    public class Component {
        private String componentName;
        private String componentDescription;
        private String[] specifications;

        public Component(String componentName, String componentDescription, String[] specifications) {
            this.componentName = componentName;
            this.componentDescription = componentDescription;
            this.specifications = specifications;
        }

        public String getComponentName() {
            return componentName;
        }

        public String getComponentDescription() {
            return componentDescription;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\tName: " + componentName + "\n");
            stringBuilder.append("\tDescription: " + componentDescription + "\n");
            stringBuilder.append("\tSpecifications:\n");
            for (String specification: specifications) {
                stringBuilder.append("\t\t" + specification + "\n");
            }
            return stringBuilder.toString();
        }
    }
}

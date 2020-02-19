package DIC;

import org.junit.platform.commons.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class Container {

    //Map une string de l'annotation InjectMe avec sa valeur
    private Map<String, Object> injectMeValues;

    public Container(){
        injectMeValues = new HashMap<>();
    }

    public void register(String key, Object value){
        if(!injectMeValues.containsKey(key)){
            injectMeValues.put(key, value);
        }
        else{
            injectMeValues.replace(key, value);
        }
    }

    public Object getInstanceInjectConstructor(Class maClasse){
        Object o = null;
        List<Annotation> annotations = new ArrayList<>();
        List<Constructor> annotedConstructors = new ArrayList<>();
        System.out.println("Injection par Construteur de la classe " + maClasse.getName());

        if(maClasse.isPrimitive()){
            return primitiveToObject(maClasse);
        }

        maClasse = handleClassAnnotation(maClasse);


        try {
            for(Constructor c : maClasse.getDeclaredConstructors()){
                if(c.isAnnotationPresent(InjectMe.class)){
                    annotedConstructors.add(c);
                }
            }
            if(annotedConstructors.size()>0) {
                List<Object> params = new ArrayList<>();
                Constructor annotedConstructor = annotedConstructors.get(0);

                for (Parameter p: annotedConstructor.getParameters()) {
                    if(p.isAnnotationPresent(InjectMe.class)){
                        System.out.println("Paramètre annoté: " + p.getName());

                        String key = p.getAnnotation(InjectMe.class).key();
                        if(key.compareTo("") != 0 && injectMeValues.containsKey(key)){
                            params.add(injectMeValues.get(key));
                        }
                        else {
                            params.add(getInstanceInjectConstructor(p.getType()));
                        }
                    }
                    else {
                        params.add(getInstanceInjectConstructor(p.getType()));
                    }
                }
                o = annotedConstructor.newInstance(params.toArray());
            }
            else{
                o = maClasse.newInstance();
            }

            return o;
        }
        catch (Exception e){
            System.out.println("Erreur lors de l'injection par constructeur " + e.getMessage() );
            return o;
        }
    }

    public Object getInstanceInjectSetters(Class maClasse){
        Object o = null;
        List<Annotation> annotations = new ArrayList<>();
        List<Method> annotedSetters = new ArrayList<>();

        if(maClasse.isPrimitive()){
            return primitiveToObject(maClasse);
        }

        maClasse = handleClassAnnotation(maClasse);

        System.out.println("Injection par Setters de la classe " + maClasse.getName());

        try {
            o = maClasse.newInstance();

            for(Method m : maClasse.getMethods()){
                if(m.getName().startsWith("set") && m.getParameterTypes().length == 1 && m.isAnnotationPresent(InjectMe.class)){
                    annotedSetters.add(m);
                }
            }


            for (Method m: annotedSetters) {
                System.out.println("Setter annoté: " + m.getName());
                String key = m.getAnnotation(InjectMe.class).key();
                if(key.compareTo("") != 0 && injectMeValues.containsKey(key)){
                    m.invoke(o, injectMeValues.get(key));
                }
                else {
                    m.invoke(o, getInstanceInjectSetters(m.getParameterTypes()[0]));
                }
            }

            return o;
        }
        catch (Exception e){
            System.out.println("Erreur lors de l'injection par setters " + e.getCause() + " " +e.getMessage() );
            return o;
        }
    }

    public Object getInstanceInjectFields(Class maClasse){
        Object o = null;
        List<Annotation> annotations = new ArrayList<>();
        List<Field> annotedFields = new ArrayList<>();
        System.out.println("Injection par Fields de la classe " + maClasse.getName());

        if(maClasse.isPrimitive()){
            return primitiveToObject(maClasse);
        }

        maClasse = handleClassAnnotation(maClasse);

        try {
            o = maClasse.newInstance();

            Class<?> currentClass = maClasse;

            while(currentClass != null){

                for(Field f : currentClass.getDeclaredFields()){
                    if(f.isAnnotationPresent(InjectMe.class)){
                        annotedFields.add(f);
                    }
                }

                currentClass = currentClass.getSuperclass();
            }

            for (Field f: annotedFields) {
                boolean accessible = f.isAccessible();
                f.setAccessible(true);

                System.out.println("Field annoté: " + f.getName());

                String key = f.getAnnotation(InjectMe.class).key();
                if(key.compareTo("") != 0 && injectMeValues.containsKey(key)){
                    f.set(o, injectMeValues.get(key));
                }
                else{
                    f.set(o, getInstanceInjectFields(f.getType()));
                }
                f.setAccessible(accessible);
            }

            return o;
        }
        catch (Exception e){
            System.out.println("Erreur lors de l'injection par fields " + e.getMessage() );
            return o;
        }
    }

    private static Object primitiveToObject(Class myClass) {
        Object obj;

        if(myClass == boolean.class){
            obj = new Boolean(false);
        }
        else if(myClass == byte.class){
            byte b = 0;
            obj = new Byte(b);
        }
        else if(myClass == short.class) {
            short s = 0;
            obj = new Short(s);
        }
        else if(myClass == int.class){
            obj = new Integer(0);
        }
        else if(myClass == long.class){
            obj = new Long(0);
        }
        else if(myClass == float.class){
            obj = new Float(0f);
        }
        else if(myClass == double.class) {
            obj = new Double(0f);
        }
        else if(myClass == char.class) {
            obj = new Character(Character.MIN_VALUE);
        }
        else {
            obj = Void.class;
        }

        return obj;
    }

    private Class handleClassAnnotation(Class maClasse){
        Class wantedClass = maClasse;

        try {
            if( maClasse.getDeclaredAnnotation(InjectMe.class) != null){
                String key = maClasse.getName();
                String classeS = (String) injectMeValues.get(key);

                if(classeS!=null){
                    wantedClass = Class.forName(classeS);
                }
            }
        }
        catch (Exception e){
            System.out.println("Erreur lors de la conversion de classe " + e.getMessage() + " " + e.getCause() );
        }
        finally {
            return wantedClass;
        }
    }
}

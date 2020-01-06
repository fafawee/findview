package com.fagawee.flib.ioc;

import android.app.Activity;
import android.view.View;

import com.fagawee.flib.ioc.view.ViewById;
import com.fagawee.flib.ioc.view.ViewClick;
import com.fagawee.flib.ioc.view.ViewFinder;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Mr.Tian on 2019/12/19.
 *
 */

public class FInject {


    public static void inject(Activity activity)
    {
        inject(new ViewFinder(activity),activity);
    }
    public static void inject(View view)
    {
        inject(new ViewFinder(view),view);
    }


    private static void inject(ViewFinder finder,Object obj)
    {
        injectField(finder,obj);
        injectEvent(finder,obj);
    }

    private static void injectEvent(ViewFinder finder, final Object obj) {
        Class<?> cls=obj.getClass();
        if(cls!=null)
        {
            Method[] methods=cls.getDeclaredMethods();
            if(methods!=null&&methods.length>0)
            {
                for (int i = 0; i < methods.length; i++) {
                    final Method method=methods[i];
                    method.setAccessible(true);
                    ViewClick viewClick=method.getAnnotation(ViewClick.class);
                    if(viewClick!=null)
                    {
                        int[] res=viewClick.value();
                        if(res!=null)
                        {
                            for (int j = 0; j <res.length ; j++) {
                                final View view=finder.findViewById(res[j]);
                                if(view!=null)
                                {
                                    view.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                           Class<?>[] paramsType= method.getParameterTypes();
                                           if(paramsType.length==0)
                                           {
                                               try {
                                                   method.invoke(obj);
                                               } catch (IllegalAccessException e) {
                                                   e.printStackTrace();
                                               } catch (InvocationTargetException e) {
                                                   e.printStackTrace();
                                               }
                                           }
                                           else
                                           {
                                               if(paramsType.length==1&&View.class.isAssignableFrom(paramsType[0]))
                                               {
                                                   try {
                                                       method.invoke(obj,view);
                                                   } catch (IllegalAccessException e) {
                                                       e.printStackTrace();
                                                   } catch (InvocationTargetException e) {
                                                       e.printStackTrace();
                                                   }
                                               }

                                           }

                                        }
                                    });
                                }

                            }
                        }


                    }

                }

            }
        }
    }

    private static void injectField(ViewFinder finder, Object obj) {
        Class<?> cls=obj.getClass();
        if(cls!=null)
        {
            Field[] fields=cls.getDeclaredFields();
            if(fields!=null&&fields.length>0)
            {
                for (int i = 0; i < fields.length; i++) {
                    Field field=fields[i];
                    field.setAccessible(true);
                    ViewById annotation=field.getAnnotation(ViewById.class);
                    if(annotation!=null)
                    {
                        int res=annotation.value();
                        View view=finder.findViewById(res);
                        Class<?> viewClass=view.getClass();
                        Class<?> fieldClass=field.getType();
                        if(view!=null&&fieldClass.isAssignableFrom(viewClass))
                        {
                            try {
                                field.set(obj,view);

                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
            }

        }


    }








}

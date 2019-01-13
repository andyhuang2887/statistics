package com.hza;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "count", defaultPhase = LifecyclePhase.PACKAGE)
public class StatisticsMojo extends AbstractMojo {

	@Parameter(property = "basePath")
	private String basePath;
	private int number = 0;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		File file = new File(basePath + "/src");
		if (file.exists()) {
			countJavaFiles(file);
		}
		System.out.println("total Java Files : "+number);
	}

	private void countJavaFiles(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files) {
				countJavaFiles(f);
			}
		} else {
			String fileName = file.getName();
			if (fileName.substring(fileName.lastIndexOf(".")).equals(".java")) {
				System.out.println(file.getPath());
				number++;
			}
		}
	}
}

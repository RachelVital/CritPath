package org.prisma.critpath.domain;

import java.util.List;

public class Repository {

    private long id;
    private String owner;
    private String repository;
    private String zenhubRepoId;
    private String acessTokenGit;
    private String iterationName;
    
    private List<NetworkPath> listNetworkPath;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getRepository() {
		return repository;
	}
	public void setRepository(String repository) {
		this.repository = repository;
	}
	public String getZenhubRepoId() {
		return zenhubRepoId;
	}
	public void setZenhubRepoId(String zenhubRepoI) {
		this.zenhubRepoId = zenhubRepoI;
	}
	public String getAcessTokenGit() {
		return acessTokenGit;
	}
	public void setAcessTokenGit(String acessTokenGit) {
		this.acessTokenGit = acessTokenGit;
	}
	public List<NetworkPath> getListNetworkPath() {
		return listNetworkPath;
	}
	public void setListNetworkPath(List<NetworkPath> listNetworkPath) {
		this.listNetworkPath = listNetworkPath;
	}
	public String getIterationName() {
		return iterationName;
	}
	public void setIterationName(String iterationName) {
		this.iterationName = iterationName;
	}
	
	


}
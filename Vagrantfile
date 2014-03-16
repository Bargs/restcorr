Vagrant.configure("2") do |config|
    config.vm.box = "precise64"

    config.vm.network "private_network", ip: "192.168.40.2"
    config.vm.network "public_network"

    config.vm.provision "docker"

    config.vm.provision "ansible" do |ansible|
        ansible.playbook = "ansible/db.yml"
        ansible.inventory_path = "ansible/local"
    end
end

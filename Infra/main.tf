provider "aws" {
  region = var.region
}

# Get the default VPC
data "aws_vpc" "default" {
  default = true
}

# Get the default subnet
data "aws_subnets" "default" {
  vpc_id = data.aws_vpc.default.id
}

# Get the default AMI for Amazon Linux
data "aws_ami" "amazon_linux" {
  most_recent = true
  owners      = ["amazon"]

  filter {
    name   = "name"
    values = ["amzn2-ami-hvm-2.0.*-x86_64-gp2"]
  }
  filter {
    name   = "state"
    values = ["available"]
  }
}

# Create a security group in the default VPC
resource "aws_security_group" "default_sg" {
  name_prefix = "default-sg"
  description = "Allow inbound SSH traffic"
  vpc_id      = data.aws_vpc.default.id

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

# Create an EC2 instance in the default VPC
resource "aws_instance" "default" {
  ami                   = data.aws_ami.amazon_linux.id
  instance_type         = var.instance_type
  subnet_id             = element(data.aws_subnets.default.ids, 0)  # Use the first default subnet
  vpc_security_group_ids = [aws_security_group.default_sg.id]
  tags = {
    Name = "default-ec2-instance"
  }
}
